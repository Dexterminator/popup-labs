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

            Graph<Void, Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addDirectedEdge (u, v, new Weight (w));
            }

            Dijkstra.DijkstraResult dijkstraResult = Dijkstra.shortestPath (graph, graph.getVertices ().get (s));
            long[] distance = dijkstraResult.distance;
            for (int i = 0; i < q; i++) {
//                io.println ("new query");
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

    public static void printPath (int vertexId, Vertex[] parent) {
        int currentId = vertexId;
        List<Integer> ids = new ArrayList<> ();
        while (true) {
            ids.add (currentId);
            Vertex vertex = parent[currentId];
            if (vertex == null)
                break;
            currentId = vertex.getId ();
        }

        for (int i = ids.size () - 1; i >= 0; i--) {
            io.print (ids.get (i) + " ");
        }
        io.println ();
    }
}
