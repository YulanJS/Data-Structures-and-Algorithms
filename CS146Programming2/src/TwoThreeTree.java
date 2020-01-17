public class TwoThreeTree 
{ 
	
	private Node root;
	
	public TwoThreeTree() {
		root = null;
	}
	
	//If the attribute of a class is never used in all methods, those methods should belong
	//to another class. Here, they should all belong to the Node class.
	
	public String search(int key) 
	{
		if(root == null)
		{
			return "";
		}
		return root.findRelatedNode(key).toString();
	}
	
	public boolean insert(int aNum) {
		if(root == null)
		{
			root = new Node(aNum);
			return true;
		}
		Node newNode= root.add(aNum);
		if(newNode == null)
			return false;
		if(newNode.isOverFull())
		{
			root = newNode.split();
			return true;
		}
		root = newNode;
		return true;
	}
	
	class Node 
	{
		public int MAX_KEY_NUM = 2;
		// actual number of keys, 1 or 2 in 23 tree. 
		//use numOfKeys to control arrays of keys and children.
		public int numOfKeys; 
		// declare more than one, to convenience split
		public int[] keys = new int[MAX_KEY_NUM + 1];
		public Node[] children = new Node[MAX_KEY_NUM + 2];
		//public Node parent = null;//should not have parent reference
		
		public Node(int d)
		{
			keys[0] = d;
			numOfKeys = 1;
			//auto sets all children to null?
			children[0] = null;
			children[1] = null;
	 	}
	
		public Node(int k, Node node1, Node node2)
		{
			//use this constructor to merge...
			keys[0] = k;
			numOfKeys = 1;
			children[0] = node1;
			children[1] = node2;
		}
		public Node findRelatedNode(int data) {
			// given this code is not null...continue as below
			// return a node, if the node has data as a value
			// if no node has data as a value, return a leaf node
			if (isLeaf()) {
				// no matter the leaf contains "data" or not, returns it.
				return this;
			}
			// not a leaf, needs to decide branch.
			int i = findPositionInNode(data);
			if(i < numOfKeys && data == keys[i])
				return this;
			// go to branch i
			return children[i].findRelatedNode(data);
		}
		

		public Node add(int data)
		{
			int i = findPositionInNode(data); // I can get i = numOfKeys here.
			Node nodeSplit;
			//nodeSplit is a standard 23 tree. Either get it from a overfull child and split it.
			//Or when the current node is a leaf,create a node with data as key and two null children.
			//both of the cases can use the same way to merge nodeSplit with current node.
			if(i < numOfKeys && data == keys[i])
			{
				//found duplicate value in a node. no need to go further.
				return null;
			}
			if(isLeaf())
			{
				nodeSplit = new Node(data);
			}
			else
			{
				Node returnedNode = children[i].add(data);
				if(returnedNode == null)
				{
					return null;
				}
				if(!(returnedNode.isOverFull()))
				{
					return this;
				}
				nodeSplit = returnedNode.split();//after splitting
			}
			
			shift(i);
			keys[i] = nodeSplit.keys[0];
			children[i] = nodeSplit.children[0];
			children[i + 1] = nodeSplit.children[1];
			numOfKeys ++;	
			return this;
		}
				
		public int findPositionInNode(int data) {
			int i = numOfKeys;
			while (i >= 1 && data <= keys[i - 1]) 
			{
				//revise here to let it be the same index for two possibilities:
				//index for go to branch i or index for check duplicate with keys[i] 
				//just change < to <=
				//the returned index can be as large as numOfKeys OR can be as small as 0
				i--;
			}
			return i;
		}
		
		public Node split()
		{
			//currently, this split is only for 23 tree.
			//current node is overful, so split it.
			//I don't know if I can use children[0],children[1],children[2] here...
			//If use, it is a 23 tree. 
			Node leftNode = new Node(keys[0], children[0], children[1]);
			//System.out.println(leftNode);
			Node rightNode = new Node(keys[2], children[2], children[3]);
			//System.out.println(rightNode);
			Node newNode = new Node(keys[1],leftNode,rightNode);
			//System.out.println(newNode);
			return newNode;
		}
		public void shift(int index)
		{
			//shift all keys from children[numOfKeys - 1] to keys[index].
			int i = numOfKeys - 1;
			while(i >= index)
			{
				children[i + 2] = children[i + 1];
				keys[i + 1] = keys[i];
				i--;
			}
			//didn't change numOfKeys yet
		}
	
		public boolean isOverFull() 
		{
			return numOfKeys > MAX_KEY_NUM;
		}
		
		
		public boolean isLeaf()// nonempty, has at least a key inside
		{
			for (int i = 0; i <= numOfKeys; i++) 
			{
				//check until i, correct.key[0] to key[n-1], has n children.
				if (children[i] != null) 
				{
					// for loop: children[i].numOfKeys != 0
					return false;
				}
			}
			return true;
		}
		
		// show the string form of a nonempty node
		public String toString() // not empty node
		{
			String str = "";
			//wired but I want to have a correct format to print out
			str += keys[0];
			int i = 1;
			while(i < numOfKeys)
			{
				str += " " + keys[i];
				i++;
			}
			return str;
		}
	}
}

