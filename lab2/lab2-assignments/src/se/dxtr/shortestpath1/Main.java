package se.dxtr.shortestpath1;

import se.dxtr.graphlibrary.Dijkstra;
import se.dxtr.graphlibrary.Graph;
import se.dxtr.graphlibrary.Kattio;
import se.dxtr.graphlibrary.Weight;

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

            Graph<Void, Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addDirectedEdge (u, v, new Weight (w));
            }

            Dijkstra.shortestPath (graph, graph.getVertices ().get (s));
            for (int i = 0; i < q; i++) {

            }
        }
    }
}
