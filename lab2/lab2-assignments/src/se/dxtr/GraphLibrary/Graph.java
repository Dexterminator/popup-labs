package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dexter on 05/10/15.
 */
public class Graph<E> {
    private final List<Vertex<E>> vertices;
    private final List<Edge<E>> edges;

    public Graph (int size) {
        vertices = new ArrayList<> ();
        edges = new ArrayList<> ();
        for (int i = 0; i < size; i++)
            vertices.add (new Vertex<> (i));
    }

    public void addEdge (int from, int to) {
        addEdge (from, to, null);
    }

    public void addEdge (int from, int to, E data) {
        Vertex<E> fromVertex = vertices.get (from);
        Vertex<E> toVertex = vertices.get (to);
        Edge<E> edge = new Edge<> (fromVertex, toVertex, data);
        edges.add (edge);
        fromVertex.addEdge (edge);
        toVertex.addEdge (edge);
    }

    public void addDirectedEdge (int from, int to, E data) {
        Vertex<E> fromVertex = vertices.get (from);
        Vertex<E> toVertex = vertices.get (to);
        Edge<E> edge = new Edge<> (fromVertex, toVertex, data);
        edges.add (edge);
        fromVertex.addEdge (edge);
    }

    public List<Edge<E>> getEdges () {
        return Collections.unmodifiableList (edges);
    }

    public List<Vertex<E>> getVertices () {
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
