package se.dxtr.eulerianpath;

import se.dxtr.graphlibrary.*;

import java.util.List;

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

            List<Vertex<Visited, Void>> eulerianPath = EulerianPathFinder.findEulerianPath (graph);
            if (eulerianPath == null) {
                io.println ("Impossible");
            } else {
                for (Vertex<Visited, Void> vertex : eulerianPath)
                    io.print (vertex.getId () + " ");
                io.println ();
            }
        }

        io.close ();
    }
}
