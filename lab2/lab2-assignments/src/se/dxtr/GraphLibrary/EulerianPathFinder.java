package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Created by dexter on 06/10/15.
 */
public class EulerianPathFinder {

    public static List<Edge<Void>> findEulerianPath (Graph<Void> graph) {
        Deque<Edge<Void>> forward = new LinkedList<> ();
        Deque<Edge<Void>> backTrack = new LinkedList<> ();
        Map<Integer, Deque<Edge<Void>>> unvisited = new HashMap<> ();

        Vertex<Void> start = null;
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

        Deque<Edge<Void>> currentVertexEdges = unvisited.get (start.getId ());
        while (!currentVertexEdges.isEmpty ()) {
            Edge<Void> currentEdge = currentVertexEdges.poll ();
            forward.push (currentEdge);
            currentVertexEdges = unvisited.get (currentEdge.getTo ().getId ());
        }

        while (!forward.isEmpty ()) {
            Edge<Void> edge = forward.pop ();
            backTrack.push (edge);
            currentVertexEdges = unvisited.get (edge.getFrom ().getId ());
            while (!currentVertexEdges.isEmpty ()) {
                edge = currentVertexEdges.poll ();
                forward.push (edge);
                currentVertexEdges = unvisited.get (edge.getTo ().getId ());
            }
        }

        if (backTrack.size () != graph.getEdges ().size ())
            return null;

        List<Edge<Void>> path = new ArrayList<> ();
        while (!backTrack.isEmpty ())
            path.add (backTrack.pop ());

        return path;
    }
}
