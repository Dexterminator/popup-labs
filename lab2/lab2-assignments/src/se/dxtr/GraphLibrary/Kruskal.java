package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Utility class with a method for finding the minimum spanning tree of a graph.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Kruskal {
    static Comparator<Edge<Weight>> weightEdgeComparator =
            (edge1, edge2) -> edge1.getData ().weight - edge2.getData ().weight;

    /**
     * Finds the minimum spanning tree of a graph, if ther is one, using Kurskal's algorithm.
     *
     * @param graph the graph in which to find the minimum spanning tree
     * @return a list of edges representing the minimum spanning tree if one exists, null otherwise
     */
    static public List<Edge<Weight>> kruskal (Graph<Weight> graph) {
        DisjointSets sets = new DisjointSets (graph.size ());
        List<Edge<Weight>> edges = new ArrayList<> (graph.getEdges ());
        edges.sort (weightEdgeComparator);
        List<Edge<Weight>> tree = new ArrayList<> ();

        // Use disjoint sets to represent a forest of trees, and keep adding edges to the result tree
        // if they connect two different trees. If the graph is connected, the forest and the result forms
        // a minimum spanning tree.
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

    /**
     * The tree is a spanning tree if it includes all vertices in the graph, i.e  the number of edges is |vertices| - 1.
     */
    private static boolean isSpanningTree (List<Edge<Weight>> tree, Graph<Weight> graph) {
        return tree.size () == graph.size () - 1;
    }

}
