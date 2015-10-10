package se.dxtr.shortestpath3;

import se.dxtr.graphlibrary.*;

/**
 * Solve shortestpath3 kattis problem.
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
            int s = io.getInt ();
            if (n == 0 && m == 0 && q == 0 && s == 0)
                break;

            Graph<Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addDirectedEdge (u, v, new Weight (w));
            }

            DistanceResult bellmanResult = BellmanFord.shortestPath (graph, graph.getVertices ().get (s));
            long[] distance = bellmanResult.distance;
            for (int i = 0; i < q; i++) {
                int to = io.getInt ();
                if (distance[to] == Integer.MAX_VALUE)
                    io.println ("Impossible");
                else if (distance[to] == Integer.MIN_VALUE)
                    io.println ("-Infinity");
                else
                    io.println (distance[to]);
            }
            io.println ();
        }

        io.close ();
    }
}
