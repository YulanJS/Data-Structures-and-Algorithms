import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class JobScheduleTest {
	private JobSchedule schedule;

	
	@Before
	public void testLinear() 
	{
		schedule = new JobSchedule();
		JobSchedule.Job j0 = schedule.addJob(3);
		// why can use in this way? inner class... addJob returns Job type...
		System.out.println(j0.getStartTime());
		System.out.println(schedule.minCompletionTime());

		JobSchedule.Job j1 = schedule.addJob(2);
		System.out.println(j1.getStartTime());
		System.out.println(schedule.minCompletionTime());

		schedule.getJob(1).requires(schedule.getJob(0));
		System.out.println(j0.getStartTime());
		System.out.println(j1.getStartTime());
		System.out.println(schedule.minCompletionTime());
		
		JobSchedule.Job j2 = schedule.addJob(7);
		System.out.println(j2.getStartTime());
		System.out.println(schedule.minCompletionTime());

		schedule.getJob(2).requires(schedule.getJob(1));
		System.out.println(j0.getStartTime());
		System.out.println(j1.getStartTime());
		System.out.println(j2.getStartTime());
		System.out.println(schedule.minCompletionTime());
		
		schedule.getJob(2).requires(schedule.getJob(0));
		System.out.println(j0.getStartTime());
		System.out.println(j1.getStartTime());
		System.out.println(j2.getStartTime());
		System.out.println(schedule.minCompletionTime());
		
		JobSchedule.Job j3 = schedule.addJob(6);
		schedule.getJob(3).requires(schedule.getJob(2));
		System.out.println(j0.getStartTime());
		System.out.println(j1.getStartTime());
		System.out.println(j2.getStartTime());
		System.out.println(j3.getStartTime());
		System.out.println(schedule.minCompletionTime());
	}
	
	@Test
	public void testCycle() {		
		//test cycles
		schedule.getJob(2).requires(schedule.getJob(3));
		System.out.println(schedule.getJob(0).getStartTime());
		System.out.println(schedule.getJob(1).getStartTime());
		System.out.println(schedule.getJob(2).getStartTime());
		System.out.println(schedule.getJob(3).getStartTime());
		System.out.println(schedule.minCompletionTime());
	}
	
//	@Test
//	public void testAddJobAfterCompletion()
//		//test add job
//		JobSchedule.Job j4 = schedule.addJob(10);
//		System.out.println(j0.getStartTime());
//		System.out.println(j1.getStartTime());
//		System.out.println(j2.getStartTime());
//		System.out.println(j3.getStartTime());
//		System.out.println(j4.getStartTime());
//		System.out.println(schedule.minCompletionTime());
//	}
	
//	@Test
//	public void testNewRequirement()
//	{
//		
//	}
	
	

}
