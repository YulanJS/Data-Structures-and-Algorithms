import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;


public class MaxHeapTest
{
   private MaxHeap heap; //heap is an object of MaxHeap class.
   //It is also an attribute of MaxHeapText class.


   @Before
   public void setUp() throws Exception
   {
      heap = new MaxHeap(10);
      //build a max heap through insert
      heap.insert(new Student("Susan", 60, 3.5));
      heap.insert(new Student("Ben", 70, 3.4));
      heap.insert(new Student("Reed", 120, 4.0));
      heap.insert(new Student("Johnny", 50, 1.2));
      heap.insert(new Student("a", 55, 2.9));
      heap.insert(new Student("b", 63, 3.8));
      heap.insert(new Student("c", 57, 3.2));
      heap.insert(new Student("d", 69, 3.6));
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
	   Student s = new Student("Ben", 70, 3.4);
	   s.setIndex(3);
	   // By the insert order, using heap properties, I can figure out that gpa 3.4
	   // should be at index 3 of the heap. So I created a new object. As long as the
	   // index is the same, changeKey will find it.
	   // This way, we don't need to use multiple ExtractMax to return original elems 
	   //in heap to check ChangeKey
	   heap.changeKey(s, 3.9);
       heap.extractMax();
       assertEquals(3.9, heap.extractMax().gpa(), .000001);
	   
   }

   @Test 
   public void decreaseKey()
   {
	   Student s1 = new Student("d", 69, 3.6);
	   s1.setIndex(1);
	   heap.changeKey(s1, 3.3);
	   assertEquals(4.0, heap.extractMax().gpa(), .000001);
       assertEquals(3.8, heap.extractMax().gpa(), .000001);
       assertEquals(3.5, heap.extractMax().gpa(), .000001);
       assertEquals(3.4, heap.extractMax().gpa(), .000001);
       assertEquals(3.3, heap.extractMax().gpa(), .000001);
       assertEquals(3.2, heap.extractMax().gpa(), .000001);
	   
   }
}
