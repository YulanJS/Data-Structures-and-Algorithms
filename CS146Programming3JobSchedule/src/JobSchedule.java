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
		topologicalSorting();
		relaxEdges();
		//don't only consider ending early, take the responsibility of relaxing all possible edges as well... 
		//remember, minCompletionTime and getStartTime are the same algorithm. Imagine, there is a dummy Job object,
		//sitting at the end of every object, the start time of the dummy is minCompletionTime.
		return minCompletionTime;
	}

	private void topologicalSorting() {
		topologicalJobs = new ArrayList<>();
		// every time needs topological sorting, needs a new ArrayList to store
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
			}
			jobIndex++;
		}

		int topoIndex = 0;
		while(topoIndex < topologicalJobs.size())
		{
			Job currentJob = topologicalJobs.get(topoIndex); 
			//ArrayList behaves like array, it can directly go and get arraylist.get(i)
			//Iterator cannot work properly when you add something to the ArrayList while traversing
			//so use index to traverse it yourself.
			Iterator<Job> nextJobsIter = currentJob.nextJobs.iterator();
			while (nextJobsIter.hasNext()) {
				Job nextJob = nextJobsIter.next();
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
			//This part, may not need it... check 0 and set to false...
			//At the end of relaxEdges, it will set newRequirement to false.
			minCompletionTime = -1;
		}
		// put all vertices with in-degree 0 in the list already. Others are in cycles.
	}

	//can you do topological sort and relax edges in the same time?
	private void relaxEdges() {
		Iterator<Job> topoJobIter = topologicalJobs.iterator();
		while (topoJobIter.hasNext()) {
			Job currentJob = topoJobIter.next();
			//Logic error: not relaxing all edges it points to... only relaxing edges to those are not in cycles...
			//Those vertices in cycles need to keep the initialized value -1.
			Iterator<Job> nextJobsIter = currentJob.nextJobs.iterator();
			while (nextJobsIter.hasNext()) {
				Job nextJob = nextJobsIter.next();
				if(nextJob.startTime != -1) //nextJob is in this topological order.
				{
					if (currentJob.startTime + currentJob.timeSpentToFinish > nextJob.startTime) {
						nextJob.startTime = currentJob.startTime + currentJob.timeSpentToFinish;
					}
					//If minCompletionTime is -1, don't need to update.
					if (minCompletionTime != -1 && nextJob.startTime + nextJob.timeSpentToFinish > minCompletionTime) {
						minCompletionTime = nextJob.startTime + nextJob.timeSpentToFinish;
					}
				}
				
			}
		}
		newRequirement = false;
	}

	// inner class JobSchedule.Job
	class Job {
		// in Khan's...only in-degree matters, instead of actual incoming vertices.
		private int timeSpentToFinish;
		private int startTime;
		private ArrayList<Job> nextJobs; // out going
		private int numOfPrerequisites; // numOf in-degrees. //in-degree should not be changed... no matter how many
										// topological sorting have been run...
		private int copyOfPrerequisitesNum;
		private boolean isInCycle;

		// private constructor
		private Job(int time) {
			timeSpentToFinish = time;
			nextJobs = new ArrayList<>();
		}

		public void requires(Job j)// This is the required method declaration.
		{
			// This may change minCompletionTime... and getStartTime()... but not always!
			// but not computing now... When user asks for getStartTime() or
			// minCompletionTime... Learn to do it later.
			// needs a symbol to show that something has been changed...
			numOfPrerequisites++;
			// Job j, also belongs to Job class, like other.value in compareTo(), I can directly get its fields
			j.nextJobs.add(this);// converts this edge to out-going edge.
			
			//When should set newRequirement to true is the most important part of this assignment.
			if(j.startTime == -1 && startTime != -1)
			{
				startTime = -1;
				newRequirement = true;
			}
			else if(j.startTime != -1 && startTime != -1 && j.startTime + j.timeSpentToFinish > startTime)
			{
				//consider four possibilities: j.start == -1 or nonnegative, this.start == -1 or nonnegative
				newRequirement = true; // tell outer class that something has been changed.
			//you previously set it to true, but never set it back to false...
			}
		}

		public int getStartTime() {
			if (!newRequirement) {
				return startTime;
			}
			topologicalSorting();
			// if this job is in a cycle, it is not going to be finished...
			// If there is a cycle, but this job is not in the cycle...This job can be finished.
			relaxEdges();
			return startTime;
		}
	}
}
