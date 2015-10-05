package se.dxtr;

import java.util.Comparator;
import java.util.Optional;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    private static Comparator<UndirectedEdge<Integer>> edgeComparator =
            Comparator.comparing (UndirectedEdge<Integer>::getVertexA)
            .thenComparing (UndirectedEdge<Integer>::getVertexB);


    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();

            if (n == 0 && m == 0)
                break;

            UndirectedGraph<Integer> graph = new UndirectedGraph<> ();
            for (int i = 0; i < n; i++)
                graph.addVertex (new Vertex<> (i));

            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addEdge (new Vertex<> (u), new Vertex<> (v), w);
            }

            Optional<UndirectedGraph<Integer>> minimumSpanningTree = Kruskal.minimumSpanningTree (graph);
            if (minimumSpanningTree.isPresent ()) {
                printSortedTree (minimumSpanningTree.get ());
            } else {
                io.println ("Impossible");
            }
        }
        io.close ();
    }

    private static void printSortedTree (UndirectedGraph<Integer> tree) {
        io.println (tree.getCost ());
        tree.getEdges ().stream ()
                .sorted (edgeComparator)
                .forEach (edge -> io.println (edge.getVertexA ().getId () + " " + edge.getVertexB ().getId ()));
    }
}
