package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dexter on 06/10/15.
 */
public class EulerianPathFinder {

    public static List<Vertex<Void, Void>> findEulerianPath (Graph<Void, Void> graph) {
        List<Vertex<Void, Void>> oddDegreeVertices = graph.getVertices ().stream ()
                .filter (vertex -> vertex.degree () % 2 != 0)
                .collect (Collectors.toList ());

        Vertex<Void, Void> current;
        if (oddDegreeVertices.size () == 0) {
            current = graph.getVertices ().get (0);
        } else if (oddDegreeVertices.size () == 2) {
            current = oddDegreeVertices.get (0);
        } else {
            return null;
        }

        Deque<Vertex<Void, Void>> stack = new LinkedList<> ();
        List<Vertex<Void, Void>> path = new ArrayList<> ();
        int removed = 0;
        while (true) {
            if (current.degree () == 0) {
                path.add (current);
                if (stack.isEmpty ())
                    break;
                current = stack.pop ();
            } else {
                stack.push (current);
                Vertex<Void, Void> next = current.getNeighbor (0);
                current.removeEdge (next);
                next.removeEdge (current);
                current = next;
                removed++;
            }
        }

        if (removed != graph.getEdges ().size ())
            return null;
        return path;
    }
}
