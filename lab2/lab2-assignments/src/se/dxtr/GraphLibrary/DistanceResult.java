package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Container class containing the shortest distances from a source node to all other nodes in a graph, as well as
 * the paths corresponding to the distances.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class DistanceResult {
    public final Vertex[] parent;
    public final long[] distance;

    public DistanceResult (long[] distance, Vertex[] parent) {
        this.parent = parent;
        this.distance = distance;
    }

    /**
     * Returns the shortest path to a specific vertex.
     */
    public List<Integer> getPath (int vertexId) {
        int currentId = vertexId;
        List<Integer> ids = new ArrayList<> ();
        while (true) {
            ids.add (currentId);
            Vertex vertex = parent[currentId];
            if (vertex == null)
                break;
            currentId = vertex.getId ();
        }

        Collections.reverse (ids);
        return ids;
    }
}
