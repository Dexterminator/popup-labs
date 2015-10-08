package se.dxtr.shortestpath1;

import se.dxtr.graphlibrary.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 06/10/15.
 */

public class Main {
    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();
            int q = io.getInt ();
            int s = io.getInt ();

            if (n == 0 && m == 0 && q == 0 && s == 0) {
                break;
            }

            Graph<Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addDirectedEdge (u, v, new Weight (w));
            }

            DistanceResult dijkstraResult = Dijkstra.shortestPath (graph, graph.getVertices ().get (s));
            long[] distance = dijkstraResult.distance;
            for (int i = 0; i < q; i++) {
                int vertexId = io.getInt ();
                if (distance[vertexId] != Integer.MAX_VALUE) {
                    io.println (distance[vertexId]);
                } else {
                    io.println ("Impossible");
                }
            }
            io.println ();
        }
        io.close ();
    }
}
