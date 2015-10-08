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

            Graph<Void> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                graph.addDirectedEdge (u, v);
            }

            List<Edge<Void>> eulerianPath = EulerianPathFinder.findEulerianPath (graph);
            if (eulerianPath == null) {
                io.println ("Impossible");
            } else {
                io.print (eulerianPath.get (0).getFrom ().getId () + " ");
                for (Edge<Void> edge : eulerianPath) {
                    io.print (edge.getTo ().getId () + " ");
                }
                io.println ();
            }
        }

        io.close ();
    }
}
