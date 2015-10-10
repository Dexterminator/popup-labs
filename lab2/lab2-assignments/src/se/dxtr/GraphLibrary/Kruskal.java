package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Created by dexter on 05/10/15.
 */
public class Kruskal {
    static Comparator<Edge<Weight>> weightEdgeComparator =
            (edge1, edge2) -> edge1.getData ().weight - edge2.getData ().weight;

    static public List<Edge<Weight>> kruskal (Graph<Weight> graph) {
        DisjointSets sets = new DisjointSets (graph.size ());
        List<Edge<Weight>> edges = new ArrayList<> (graph.getEdges ());
        edges.sort (weightEdgeComparator);

        List<Edge<Weight>> tree = new ArrayList<> ();
        for (Edge<Weight> edge : edges) {
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

    public static boolean isSpanningTree (List<Edge<Weight>> tree, Graph<Weight> graph) {
        return tree.size () == graph.size () - 1;
    }

}
