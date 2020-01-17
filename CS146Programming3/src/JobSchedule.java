import java.util.ArrayList;
import java.util.Iterator;

public class JobSchedule {
	private ArrayList<Job> jobs;
	private int minCompletionTime; // default is 0.
	private ArrayList<Job> topologicalJobs;
	private boolean newRequirement;

	public JobSchedule() {
		jobs = new ArrayList<>();
		topologicalJobs = new ArrayList<>();
		newRequirement = false;
	}

	public Job addJob(int time) {
		// why returns a Job type...
		// addJob, may change minCompletionTime, even if there is no prerequisite.
		// Even though all jobs start at time 0, addJob may change minCompletionTime
		Job newJob = new Job(time);
		jobs.add(newJob);
		if (minCompletionTime != -1 && minCompletionTime < time) {
			// by default, it starts at 0.
			minCompletionTime = time;
		}
		return newJob;
	}

	public Job getJob(int index) {
		return jobs.get(index);
	}

	public int minCompletionTime() {
		if (!newRequirement) {
			return minCompletionTime;
		}
		if (!topologicalSorting()) {
			return minCompletionTime;
		}
		// can have a topological order...need to relax edges.
		relaxEdges();
		//after dealing with topological order and relaxing edges, set it to false
		return minCompletionTime;
	}

	private boolean topologicalSorting() {
		// change in degree adjacency list to out degree adjacency list
		topologicalJobs = new ArrayList<>();
		// every time needs topological sorting, needs a new arraylist to store
		// find jobs whose in-degree are 0
		int jobIndex = 0;
		minCompletionTime = -1;
		while (jobIndex < jobs.size()) {
			Job job = jobs.get(jobIndex);
			job.startTime = -1;
			job.isInCycle = true;
			job.copyOfPrerequisitesNum = job.numOfPrerequisites;
			if (job.copyOfPrerequisitesNum == 0) {
				job.startTime = 0;
				job.isInCycle = false;
				topologicalJobs.add(job);
				minCompletionTime = job.timeSpentToFinish;
				System.out.println("Job " + jobIndex + " is not in cycle.");
			}
			jobIndex++;
		}

		int topoIndex = 0;
		while(topoIndex < topologicalJobs.size())
		{
			Job currentJob = topologicalJobs.get(topoIndex); 
			//ArrayList behaves like array, it can directly go and get arraylist.get(i)
			//Iterator cannot work properly when you add sth to the arraylist while traversing
			//so use index to traverse it yourself.
			Iterator<Job> nextJobsIter = currentJob.nextJobs.iterator();
			while (nextJobsIter.hasNext()) {
				Job nextJob = nextJobsIter.next();
				System.out.println("Num of Pre before decrement " + nextJob.copyOfPrerequisitesNum);
				nextJob.copyOfPrerequisitesNum--;
				if (nextJob.copyOfPrerequisitesNum == 0) {
					nextJob.startTime = 0;
					nextJob.isInCycle = false;
					topologicalJobs.add(nextJob);
				}
			}
			topoIndex++;
		}
		
		if (topologicalJobs.size() < jobs.size())
		{
			// it has a cycle, so Khan's doesn't give a complete topological order.
			if(topologicalJobs.size() == 0)
			{
				newRequirement = false;
			}
			minCompletionTime = -1;
			return false;
		}
		return true;
		// put all vertices with in-degree 0 in the list already
	}

	//can you do topological sort and relax edges in the same time?
	private void relaxEdges() {
		Iterator<Job> topoJobIter = topologicalJobs.iterator();
		while (topoJobIter.hasNext()) {
			Job currentJob = topoJobIter.next();
			//Logic error: not relaxing all edges it points to... only relaxing edges to those are not in cycles...
			//I mean those in topological order. For vertices in cycles, their startTime is -1...
			Iterator<Job> nextJobsIter = currentJob.nextJobs.iterator();
			while (nextJobsIter.hasNext()) {
				Job nextJob = nextJobsIter.next();
				if(nextJob.startTime != -1) //nextJob is in this topological order.
				{
					if (currentJob.startTime + currentJob.timeSpentToFinish > nextJob.startTime) {
						nextJob.startTime = currentJob.startTime + currentJob.timeSpentToFinish;
						System.out.println("StartTime: " + nextJob.startTime);
					}
					//If minCompletionTime is -1, don't need upadte.
					if (minCompletionTime != -1 && nextJob.startTime + nextJob.timeSpentToFinish > minCompletionTime) {
						minCompletionTime = nextJob.startTime + nextJob.timeSpentToFinish;
						System.out.println("Finish time for whole: " + minCompletionTime);
					}
				}
				
			}
		}
		newRequirement = false;
	}

	// inner class JobSchedule.Job
	class Job {
		// in Khan's...only in-degree matters, instead of actual incoming vertices.
		// Khan's don't need reverse the order to get topological order.
		private int timeSpentToFinish;
		private int startTime;
		private ArrayList<Job> nextJobs; // out going
		private int numOfPrerequisites; // numOf in-degrees. //in-degree should not be changed... no matter how many
										// topological sortings have been run...
		private int copyOfPrerequisitesNum;
		private boolean isInCycle;

		// private constructor
		private Job(int time) {
			timeSpentToFinish = time;
			nextJobs = new ArrayList<>();
		}

		public void requires(Job j)// This is the required method declaration.
		{
			// This may change minCompletionTime... and getStartTime()...
			// but not computing now... When user asks for getStartTime() or
			// minCompletionTime... Learn to do it later.
			// do topo order + relax edges... But still needs a symbol to show that
			// something has been changed...
			// requires Job j, needs more code here.
			numOfPrerequisites++;
			// Job j, also belongs to Job class, like other.value, I can directly get its
			// field
			j.nextJobs.add(this);// converts this edge to out-going edge.
			newRequirement = true; // tell outer class that sth has been changed.
			//you set to true, but never set it back to false...
		}

		public int getStartTime() {
			if (!newRequirement) {
				return startTime;
			}
			topologicalSorting();
			// if this job is in a cycle, it is not going to be finished...
			// but if the whole jobs have a cycle, and this job is not in the cycle...This
			// job is able to be finished.
			relaxEdges();
			return startTime;
		}
	}
}
