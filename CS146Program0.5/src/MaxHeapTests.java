import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import java.util.Collection;
import java.util.ArrayList;

public class MaxHeapTests {
	private MaxHeap aheap;
	private ArrayList<Student> studentsAtSchool;

	@Before
	public void setUP() throws Exception
	{
		studentsAtSchool = new ArrayList<Student>(10);
		studentsAtSchool.add(0, new Student("a", 50, 2.5));
		studentsAtSchool.add(1, new Student("b", 60, 4.0));
		studentsAtSchool.add(2, new Student("c", 70, 3.5));
		studentsAtSchool.add(3, new Student("d", 65, 3.7));
		studentsAtSchool.add(4, new Student("e", 75, 3.0));
		aheap = new MaxHeap(studentsAtSchool);
		//build a max heap through constructor
		
	}
	
	
	@Test
	public void testExtractMax()
	{
	    assertEquals(4.0, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.7, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.5, aheap.extractMax().gpa(), .000001);
	}
	
	@Test
	public void testInsert()
	{
		aheap.insert(new Student("f", 55, 3.6));
		assertEquals(4.0, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.7, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.6, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.5, aheap.extractMax().gpa(), .000001);
	}
	
	
	@Test
    public void testGetMax()
    {
	    assertEquals(4.0, aheap.getMax().gpa(), .000001);
	    assertEquals(4.0, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.7, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.5, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.0, aheap.extractMax().gpa(), .000001);
	    assertEquals(2.5, aheap.extractMax().gpa(), .000001);
	    try
	    {
	    	
		    aheap.getMax();
		    fail("Should have thrown an exception");
	    }
	    catch(IndexOutOfBoundsException e)
	    {
		    System.out.println("No element exception caught.");
	    } 
    }
	
	@Test 
    public void testDecreaseKey()
    {
	    //test an element not in the heap
	    Student s = new Student("me", 15, 3.7);
	    try
	    {
		    aheap.changeKey(s, 4.0);
		    fail("Should have thrown an exception");
	    }
	    catch(RuntimeException e)
	    {
		    System.out.println("Element not found exception caught");
	    }
	   
	    //test decreasing the key, move down
	    aheap.changeKey(studentsAtSchool.get(3), 2.0);
	    assertEquals(4.0, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.5, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.0, aheap.extractMax().gpa(), .000001);
	    assertEquals(2.5, aheap.extractMax().gpa(), .000001);
	    assertEquals(2.0, aheap.extractMax().gpa(), .000001);
    }
	
	@Test
	public void testIncreaseKey()
	{
		//test the same key, doesn't move
	    aheap.changeKey(aheap.getMax(), 4.0);
	    
	    //test increasing the key, move up
	    aheap.changeKey(studentsAtSchool.get(4), 3.8);
	    assertEquals(4.0, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.8, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.7, aheap.extractMax().gpa(), .000001);
	    assertEquals(3.5, aheap.extractMax().gpa(), .000001);
	    assertEquals(2.5, aheap.extractMax().gpa(), .000001);
	}
}
