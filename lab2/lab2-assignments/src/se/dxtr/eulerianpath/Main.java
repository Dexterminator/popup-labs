package se.dxtr.eulerianpath;

import se.dxtr.graphlibrary.Graph;
import se.dxtr.graphlibrary.Kattio;
import se.dxtr.graphlibrary.Vertex;
import se.dxtr.graphlibrary.Visited;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();

            if (n == 0 && m == 0)
                break;

            Graph<Visited, Void> graph = new Graph<> (n);
            for (Vertex<Visited, Void> vertex : graph.getVertices ())
                vertex.setData (new Visited ());

            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                graph.addEdge (u, v);
            }
            io.println (graph);
        }

        io.close ();
    }
}
