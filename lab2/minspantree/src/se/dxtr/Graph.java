package se.dxtr;

import java.util.Set;

/**
 * Created by dexter on 05/10/15.
 */
public interface Graph<T extends Comparable<T>> {

    boolean adjacent (Vertex<T> vertexA, Vertex<T> vertexB);

    Set<Vertex<T>> getNeighbors (Vertex<T> vertex);

    boolean addVertex (Vertex<T> vertex);

    boolean removeVertex (Vertex<T> vertex);

    boolean addEdge (Vertex<T> vertexA, Vertex<T> vertexB);

    boolean removeEdge (Vertex<T> vertexA, Vertex<T> vertexB);

}
