package se.dxtr.allpairspath;

import se.dxtr.graphlibrary.FloydWarshall;
import se.dxtr.graphlibrary.Graph;
import se.dxtr.graphlibrary.Kattio;
import se.dxtr.graphlibrary.Weight;

/**
 * Solve allpairspath kattis problem.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();
            int q = io.getInt ();

            if (n == 0 && m == 0 && q == 0)
                break;

            Graph<Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addDirectedEdge (u, v, new Weight (w));
            }

            int[][] distMatrix = FloydWarshall.shortestPath (graph);
            for (int i = 0; i < q; i++) {
                int from = io.getInt ();
                int to = io.getInt ();

                int dist = distMatrix[from][to];
                if (dist == Integer.MAX_VALUE)
                    io.println ("Impossible");
                else if (dist == Integer.MIN_VALUE)
                    io.println ("-Infinity");
                else
                    io.println (dist);

            }
            io.println ();
        }

        io.close ();
    }
}
