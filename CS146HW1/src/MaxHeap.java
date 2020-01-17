import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
   private ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<Student>(capacity);
   }
      
   public MaxHeap(Collection<Student> collection)
   {
	   //what is in collection? 
	   //the whole process seems to build a max heap from unordered input...
      students = new ArrayList<Student>(collection);
      //here, may be size()/2 - 1. Just one more unnecessary step.
      //search more about Collection...
      //??? Still a linear to assign the index attribute for Student objects.
      for(int i = 0; i < students.size();i++)
      {
    	  students.get(i).setIndex(i);
      }
      for(int i = size()/2; i >= 0; i--)
      {
         maxHeapify(i);
      }
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   public Student extractMax()
   {
      Student value = getMax();
      students.set(0,students.get(size()-1));
      students.get(0).setIndex(0);
      students.remove(size()-1);
      maxHeapify(0);
      return value;
   }
   
   
   public void insert(Student elt)
   {
      //Please write me.
	   students.add(elt);
	   int i = size() - 1;
	   elt.setIndex(i);
	   moveUp(i);
	   //students.get(i).setIndex(i);
   }
   
   
   public void changeKey(Student s, double newGPA) throws RuntimeException
   {
	   //This is a new version.
	   if(s.getIndex()== -1)
	   {
		   throw new RuntimeException("Not in the heap, cannot change key.");
	   }
	   
	   double oldGPA = s.gpa();
	   int i = s.getIndex();
	   students.get(i).setGPA(newGPA);
	   // This line is important. It is not s.setGPA...only Change s doesn't work
	   // I need to change it in the heap structure.
	   if(newGPA > oldGPA)
	   {
		   //increase the key
		   moveUp(i);
	   }
	   else if(newGPA < oldGPA)
	   {
		   //decrease the key
		   maxHeapify(i);
	   }
		  
      /*//Please write me.
	  //Student s must be in the arrayList, otherwise, exception
	  
		
//	    	   Student changedStudent = students.get(index);
//			   changedStudent.setGPA(newGPA);
//			   students.set(index, changedStudent);
 *             //All the above is equivalent to the next one line:
			   students.get(index).setGPA(newGPA);
	
	   }   */
   }

   private int parent(int index) 
   {
      return (index - 1)/2;
   }
   
   private int left(int index)
   {
      return 2 * index + 1;
   }
   
   private int right(int index)
   {
      return 2 * index + 2;
   }
   
   private int size()
   {
      return students.size();
   }
   
   private void swap(int from, int to)
   {
	  //swap values (the whole Student object) of the two indexes.
      Student val = students.get(from);
      students.set(from,  students.get(to));
      students.set(to,  val);
      //after swaping two objects, need to update obj.index (the attribute of obj)
      // original obj of to is at the index "from" of the ArrayList,
      // change the index attribute to from now.
      students.get(from).setIndex(from);
      students.get(to).setIndex(to);
   }
   
   private void moveUp(int i)
   {
	   while(i > 0 && students.get(i).compareTo(students.get(parent(i))) > 0) 
	   {
		   // i > 0 i.e parent(i) >= 0. students[1], students[2] both have parents.
		   swap(i, parent(i)); // swap the two Students objects.
		   //students.get(i).setIndex(i);//This was the original obj of parent(i)
		   i = parent(i);
	   }	
   }
   private void maxHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
      {
         largest = right;
      }
      if (largest != index)
      {
         swap(index, largest);
         maxHeapify(largest);
      }  
   }   
}
