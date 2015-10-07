package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ludde on 15-10-07.
 */
public class DistanceResult {
    public final Vertex[] parent;
    public final long[] distance;

    public DistanceResult(long[] distance, Vertex[] parent) {
        this.parent = parent;
        this.distance = distance;
    }


    public List<Integer> getPath (int vertexId) {
        int currentId = vertexId;
        List<Integer> ids = new ArrayList<>();
        while (true) {
            ids.add (currentId);
            Vertex vertex = parent[currentId];
            if (vertex == null)
                break;
            currentId = vertex.getId ();
        }
        Collections.reverse(ids);
        return ids;
    }
}
