package se.dxtr;

import java.util.Set;

/**
 * Created by dexter on 05/10/15.
 */
public interface Graph {

    boolean adjacent (Vertex vertexA, Vertex vertexB);

    Set<Vertex> neighbors (Vertex vertex);

    boolean addVertex (Vertex vertex);

    boolean removeVertex (Vertex vertex);

    boolean addEdge (Vertex vertexA, Vertex vertexB);

    boolean removeEdge (Vertex vertexA, Vertex vertexB);
}
