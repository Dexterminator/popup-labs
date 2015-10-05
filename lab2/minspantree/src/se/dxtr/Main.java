package se.dxtr;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();

            if (n == 0 && m == 0)
                break;

            UndirectedGraph<Integer> graph = new UndirectedGraph<> ();
            for (int i = 0; i < n; i++)
                graph.addVertex (new Vertex<> (i));

            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addEdge (new Vertex<> (u), new Vertex<> (v), w);
            }

            io.println (graph.neighbors (new Vertex<> (1)));
            io.println (graph.neighbors (new Vertex<> (0)));
            io.println (graph.getWeight (new Vertex<> (0), new Vertex<> (1)));
            break;
        }

        io.close ();
    }
}
