package se.dxtr;

import java.util.Set;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();

            if (n == 0 && m == 0)
                break;

            Graph<Void, Weight> graph = new Graph<> (n);
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

            Set<Edge<Void, Weight>> tree = Kruskal.kruskal (graph);
            if (tree != null) {
                printTree (tree);
            } else {
                io.println ("Impossible");
            }
        }
        io.close ();
    }

    public static void printTree (Set<Edge<Void, Weight>> tree) {
        long sum = 0;
        for (Edge<Void, Weight> edge : tree) {
            sum += edge.getData ().weight;
        }

        io.println (sum);
        for (Edge<Void, Weight> edge : tree) {
            io.println (edge.getFrom ().getId () + " " + edge.getTo ().getId ());
        }
    }
}
