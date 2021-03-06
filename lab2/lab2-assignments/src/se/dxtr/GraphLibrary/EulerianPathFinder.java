package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Utility class with a method for finding an eulerian path in a graph.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class EulerianPathFinder {

    /**
     * Use Hierholzer's algorithm to find an eulerian path (a path that visits every edge exactly once) in a graph,
     * if there is one.
     *
     * @param graph the graph in which to find an eulerian path
     * @return a list of edges representing the eulerian path if there is one, null otherwise
     */
    public static List<Edge<Void>> findEulerianPath (Graph<Void> graph) {
        Deque<Edge<Void>> forward = new LinkedList<> ();
        Deque<Edge<Void>> backTrack = new LinkedList<> ();
        Map<Integer, Deque<Edge<Void>>> unvisited = new HashMap<> ();
        Vertex<Void> start = null;

        // Check degrees of all vertices as the graph must have at most one vertex has (out-degree) − (in-degree) = 1,
        // and at most one vertex with (in-degree) − (out-degree) = 1 in order to have an eulerian path.
        int inoutOnes = 0;
        int outInOnes = 0;
        for (Vertex<Void> vertex : graph.getVertices ()) {
            unvisited.put (vertex.getId (), new LinkedList<> (vertex.getEdges ()));
            int inOutDiff = vertex.inDegree () - vertex.degree ();
            int outInDiff = vertex.degree () - vertex.inDegree ();

            if (inOutDiff > 0) {
                if (inOutDiff == 1) {
                    inoutOnes++;
                } else {
                    return null;
                }
            } else if (outInDiff > 0) {
                if (outInDiff == 1) {
                    start = vertex;
                    outInOnes++;
                } else {
                    return null;
                }
            }
        }

        if (!(inoutOnes == 1 && outInOnes == 1 || inoutOnes == 0 && outInOnes == 0))
            return null;

        if (start == null)
            start = graph.getVertices ().get (0);

        // Follow a path from the start vertex, registering visited edges
        Deque<Edge<Void>> currentVertexEdges = unvisited.get (start.getId ());
        while (!currentVertexEdges.isEmpty ()) {
            Edge<Void> currentEdge = currentVertexEdges.poll ();
            forward.push (currentEdge);
            currentVertexEdges = unvisited.get (currentEdge.getTo ().getId ());
        }

        // Keep following edges and adding subsequent edges to the forward path, while also registering edges
        // that need to be backtracked
        while (!forward.isEmpty ()) {
            Edge<Void> edge = forward.pop ();
            backTrack.push (edge); // This edge is part of the path
            currentVertexEdges = unvisited.get (edge.getFrom ().getId ());
            while (!currentVertexEdges.isEmpty ()) {
                edge = currentVertexEdges.poll ();
                forward.push (edge);
                currentVertexEdges = unvisited.get (edge.getTo ().getId ());
            }
        }

        // By definition, an eulerian path/circuit must contain all edges in the Graph
        if (backTrack.size () != graph.getEdges ().size ())
            return null;

        // Backtrack will at this point contain the path travelled.
        List<Edge<Void>> path = new ArrayList<> ();
        while (!backTrack.isEmpty ())
            path.add (backTrack.pop ());

        return path;
    }
}
