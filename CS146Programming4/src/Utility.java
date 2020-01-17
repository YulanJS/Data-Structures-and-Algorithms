public class Utility {
	public static boolean[] findMaximalClique(Graph g) {
		int size = g.size();
		int i = 1;
		// find Max clique num
		while (i <= size && g.hasClique(i)) {
			i++;
		}
		int maxCliqueNum = i - 1;
		boolean[] containsInMaxClique = new boolean[size];
		for (i = 0; i < size; i++) {
			containsInMaxClique[i] = false;
		}
		int j = size - 1; // vertex index
		Graph remained = g;
		Graph temp;
		while (j >= 0) {
			temp = remained.removeVertex(j);
			if (!(temp.hasClique(maxCliqueNum))) {
				containsInMaxClique[j] = true;
				// keep this vertex in remained, don't use temp
			} else {
				//
				remained = temp;
			}
			j--;
		}
		return containsInMaxClique;
	}
}
