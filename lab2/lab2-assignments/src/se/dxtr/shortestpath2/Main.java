package se.dxtr.shortestpath2;

import se.dxtr.graphlibrary.Dijkstra;
import se.dxtr.graphlibrary.Graph;
import se.dxtr.graphlibrary.Kattio;
import se.dxtr.graphlibrary.TimeTable;

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

            Graph<Void, TimeTable> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int t0 = io.getInt ();
                int p = io.getInt ();
                int d = io.getInt ();
                graph.addDirectedEdge (u, v, new TimeTable (t0, p, d));
            }

            Dijkstra.DijkstraResult result =
                    Dijkstra.shortestTimeTablePath (graph, graph.getVertices ().get (s));
            long[] distance = result.distance;

            for (int i = 0; i < q; i++) {
                int vertexId = io.getInt ();
                if (distance[vertexId] != Integer.MAX_VALUE) {
                    io.println (distance[vertexId]);
//                    printPath (vertexId, dijkstraResult.parent);
                } else {
                    io.println ("Impossible");
                }
            }
            io.println ();
        }
        io.close ();
    }
}
