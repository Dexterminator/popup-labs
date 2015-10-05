package se.dxtr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dexter on 05/10/15.
 */
public class Graph<V, E> {
    private List<Vertex<V, E>> vertices;
    private List<Edge<E, V>> edges;


    public Graph (int size) {
        vertices = new ArrayList<> ();
        edges = new ArrayList<> ();
        for (int i = 0; i < size; i++)
            vertices.add (new Vertex<> (i));
    }

    public void addEdge (int from, int to, E data) {
        Vertex<V, E> fromVertex = vertices.get (from);
        Vertex<V, E> toVertex = vertices.get (to);
        Edge<E, V> edge = new Edge<> (fromVertex, toVertex, data);
        edges.add (edge);
        fromVertex.addEdge (edge);
        toVertex.addEdge (edge);
    }

    public List<Edge<E, V>> getEdges () {
        return edges;
    }

    public List<Vertex<V, E>> getVertices () {
        return Collections.unmodifiableList (vertices);
    }

    public int size () {
        return vertices.size ();
    }

    @Override
    public String toString () {
        return "Graph{" +
                "edges=" + edges +
                '}';
    }
}
