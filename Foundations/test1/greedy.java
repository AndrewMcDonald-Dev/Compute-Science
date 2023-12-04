import java.util.Arrays;

class Greedy {
	public static void main(String[] args) {
		// Top right example
		{
			Vertice v0 = new Vertice(new int[] { 2, 3 });
			Vertice v1 = new Vertice(new int[] { 2, 3 });
			Vertice v2 = new Vertice(new int[] { 0, 1 });
			Vertice v3 = new Vertice(new int[] { 0, 1 });

			Vertice[] graph = new Vertice[] { v0, v1, v2, v3 };
			greedy_coloring(graph);
			print_graph(graph);
		}

		System.out.println();

		// Bottom Left example
		{
			Vertice v0 = new Vertice(new int[] { 1, 2, 3, 4 });
			Vertice v1 = new Vertice(new int[] { 0, 2, 3, 4 });
			Vertice v2 = new Vertice(new int[] { 1, 0, 3, 4 });
			Vertice v3 = new Vertice(new int[] { 1, 2, 0, 4 });
			Vertice v4 = new Vertice(new int[] { 1, 2, 3, 0 });

			Vertice[] graph = new Vertice[] { v0, v1, v2, v3, v4 };
			greedy_coloring(graph);
			print_graph(graph);
		}

	}

	static void greedy_coloring(Vertice[] graph) {
		for (int i = 0; i < graph.length; i++) {
			graph[i].color = get_color(graph, i);
		}
	}

	static int get_color(Vertice[] graph, int i) {
		int k = 1;
		while (true) {
			boolean valid = true;
			for (int u : graph[i].adjacency) {
				if (k == graph[u].color) {
					valid = false;
					break;
				}
			}
			if (valid) {
				return k;
			}
			k += 1;
		}
	}

	static void print_graph(Vertice[] graph) {
		for (int i = 0; i < graph.length; i++) {
			System.out.printf("V%d: Adj=%s, Color=%d\n", i, Arrays.toString(graph[i].adjacency), graph[i].color);
		}
	}

}

class Vertice {
	public int color = 0;
	public int[] adjacency;

	public Vertice(int[] adjacency) {
		this.adjacency = adjacency;
	}
}