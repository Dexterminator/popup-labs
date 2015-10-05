package se.dxtr;

import java.util.*;

/**
 * Created by dexter on 05/10/15.
 */
public class Kruskal {
    static Comparator<Edge<Weight, Void>> weightEdgeComparator =
            Comparator.comparingInt (edge -> edge.getData ().weight);

    static Comparator<Edge> lexicoGraphicalOrder = (edge1, edge2) -> {
        if (edge1.getTo ().getId () == edge2.getTo ().getId ())
            return edge1.getFrom ().getId () - edge2.getFrom ().getId ();
        return edge1.getTo ().getId () - edge2.getTo ().getId ();
    };

    static public Set<Edge<Weight, Void>> kruskal (Graph<Void, Weight> graph) {
        DisjointSets sets = new DisjointSets (graph.size ());
        List<Edge<Weight, Void>> edges = graph.getEdges ();
        edges.sort (weightEdgeComparator);

        SortedSet<Edge<Weight, Void>> tree = new TreeSet<> (lexicoGraphicalOrder);
        for (Edge<Weight, Void> edge : edges) {
            int a = edge.getFrom ().getId ();
            int b = edge.getTo ().getId ();
            if (!sets.inSameSet (a, b)) {
                tree.add (edge);
                sets.union (a, b);
            }
        }

        if (isSpanningTree (tree, graph))
            return tree;
        return null;
    }

    public static boolean isSpanningTree (SortedSet<Edge<Weight, Void>> tree, Graph<Void, Weight> graph) {
        return tree.size () == graph.size () - 1;
    }

}
