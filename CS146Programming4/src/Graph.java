public class Graph {
	public int size()
	{
		return 10;
	}
	
	public Graph removeVertex(int i)
	{
		return new Graph();
	}
	
	public boolean hasClique(int i)
	{
		if(i <= 8)
		{
			return true;
		}
		return false;
	}
}
