import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;


public class MaxHeapTest
{
   private MaxHeap heap; //heap is an object of MaxHeap class.
   //It is also an attribute of MaxHeapText class.
   private ArrayList<Student> students;


   @Before
   public void setUp() throws Exception
   {
	  students = new ArrayList<Student>();
	  //Must write this line, otherwise, invocationtargetexception init throwable line not available
	  //init, forget to initialize
      heap = new MaxHeap(10);
      //build a max heap through insert
      Student s1 = new Student("Susan", 60, 3.5);
      heap.insert(s1);
      students.add(s1);
      Student s2 = new Student("Ben", 70, 3.4);
      heap.insert(s2);
      students.add(s2);
      Student s3 = new Student("Reed", 120, 4.0);
      heap.insert(s3);
      students.add(s3);
      Student s4 = new Student("Johnny", 50, 1.2);
      heap.insert(s4);
      students.add(s4);
      Student s5 = new Student("a", 55, 2.9);
      heap.insert(s5);
      students.add(s5);
      Student s6 = new Student("b", 63, 3.8);
      heap.insert(s6);
      students.add(s6);
      Student s7 = new Student("c", 57, 3.2);
      heap.insert(s7);
      students.add(s7);
      Student s8 = new Student("d", 69, 3.6);
      heap.insert(s8);
      students.add(s8);
   }

   @Test
   public void testExtractMax()
   {
      assertEquals(4.0, heap.extractMax().gpa(), .000001);
      assertEquals(3.8, heap.extractMax().gpa(), .000001);
   }
   
   @Test
   public void testGetMax()
   {
	   assertEquals(4.0, heap.getMax().gpa(), .000001);
	   assertEquals(4.0, heap.extractMax().gpa(), .000001);
	   assertEquals(3.8, heap.extractMax().gpa(), .000001);
	   assertEquals(3.6, heap.extractMax().gpa(), .000001);
	   assertEquals(3.5, heap.extractMax().gpa(), .000001);
	   assertEquals(3.4, heap.extractMax().gpa(), .000001);
	   assertEquals(3.2, heap.extractMax().gpa(), .000001);
	   assertEquals(2.9, heap.extractMax().gpa(), .000001);
	   assertEquals(1.2, heap.extractMax().gpa(), .000001);
	   try
	   {
		   heap.getMax();
		   fail("Should have thrown an exception");
	   }
	   catch(IndexOutOfBoundsException e)
	   {
		   System.out.println("No element exception caught.");
	   } 
   }
   
   @Test 
   public void testOutsideElemChangeKey()
   {
	   //test an element not in the heap
	   Student s = new Student("me", 15, 3.7);
	   try
	   {
		   heap.changeKey(s, 4.0);
		   fail("Should have thrown an exception");
	   }
	   catch(RuntimeException e)
	   {
		   System.out.println("Element not found exception caught");
	   }
   }
   

   @Test 
   public void increaseKey()
   {
	   students.get(1).setIndex(3);
	   // By the insert order, using heap properties, I can figure out that gpa 3.4
	   // should be at index 3 of the heap. So I created a new object. As long as the
	   // index is the same, changeKey will find it.
	   // This way, we don't need to use multiple ExtractMax to return original elems 
	   //in heap to check ChangeKey
	   heap.changeKey(students.get(1), 3.9);
       heap.extractMax();
       assertEquals(3.9, heap.extractMax().gpa(), .000001);
	   
   }

   @Test 
   public void decreaseKey()
   {
	   students.get(7).setIndex(1);
	   heap.changeKey(students.get(7), 3.3);
	   assertEquals(4.0, heap.extractMax().gpa(), .000001);
       assertEquals(3.8, heap.extractMax().gpa(), .000001);
       assertEquals(3.5, heap.extractMax().gpa(), .000001);
       assertEquals(3.4, heap.extractMax().gpa(), .000001);
       assertEquals(3.3, heap.extractMax().gpa(), .000001);
       assertEquals(3.2, heap.extractMax().gpa(), .000001);
	   
   }
}
