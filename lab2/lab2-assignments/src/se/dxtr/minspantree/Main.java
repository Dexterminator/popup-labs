package se.dxtr.minspantree;

import se.dxtr.graphlibrary.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    static Comparator<Edge> lexicoGraphicalOrder = (edge1, edge2) -> {
        if (edge1.getFrom ().getId () == edge2.getFrom ().getId ())
            return edge1.getTo ().getId () - edge2.getTo ().getId ();
        return edge1.getFrom ().getId () - edge2.getFrom ().getId ();
    };

    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();

            if (n == 0 && m == 0)
                break;

            Graph<Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                if (v > u) {
                    graph.addEdge (u, v, new Weight (w));
                } else {
                    graph.addEdge (v, u, new Weight (w));
                }
            }

            List<Edge<Weight>> tree = Kruskal.kruskal (graph);
            if (tree != null) {
                Collections.sort (tree, lexicoGraphicalOrder);
                printTree (tree);
            } else {
                io.println ("Impossible");
            }
        }
        io.close ();
    }

    public static void printTree (List<Edge<Weight>> tree) {
        long sum = 0;
        for (Edge<Weight> edge : tree) {
            sum += edge.getData ().weight;
        }

        io.println (sum);
        for (Edge<Weight> edge : tree) {
            io.println (edge.getFrom ().getId () + " " + edge.getTo ().getId ());
        }
    }
}
