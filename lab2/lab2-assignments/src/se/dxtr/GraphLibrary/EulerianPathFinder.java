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
        int oddIn = 0;
        int oddOUt = 0;
        for (Vertex<Void> vertex : graph.getVertices ()) {
            unvisited.put (vertex.getId (), new LinkedList<> (vertex.getEdges ()));
            int inOutDiff = vertex.inDegree () - vertex.degree ();
            int outInDiff = vertex.degree () - vertex.inDegree ();

            if (inOutDiff > 0) {
                if (inOutDiff == 1) {
                    oddIn++;
                } else {
                    return null;
                }
            } else if (outInDiff > 0) {
                if (outInDiff == 1) {
                    start = vertex;
                    oddOUt++;
                } else {
                    return null;
                }
            }
        }

        if (!(oddIn == 1 && oddOUt == 1 || oddIn == 0 && oddOUt == 0))
            return null;

        if (start == null)
            start = graph.getVertices ().get (0);

        Deque<Edge<Void>> edges = unvisited.get (start.getId ());
        while (!edges.isEmpty ()) {
            Edge<Void> edge = edges.poll ();
            forward.push (edge);
            edges = unvisited.get (edge.getTo ().getId ());
        }

        while (!forward.isEmpty ()) {
            Edge<Void> edge = forward.pop ();
            backTrack.push (edge);
            edges = unvisited.get (edge.getFrom ().getId ());
            while (!edges.isEmpty ()) {
                edge = edges.poll ();
                forward.push (edge);
                edges = unvisited.get (edge.getTo ().getId ());
            }
        }

        List<Edge<Void>> path = new ArrayList<> ();
        while (!backTrack.isEmpty ()) {
            path.add (backTrack.pop ());
        }

        return path;
    }
}
