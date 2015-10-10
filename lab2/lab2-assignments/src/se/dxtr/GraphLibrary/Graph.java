package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generic graph class representing a graph with edges with some data associated with them.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Graph<E> {
    private final List<Vertex<E>> vertices;
    private final List<Edge<E>> edges;

    /**
     * Create a new graph with a fixed size.
     *
     * @param size the number of vertices in the graph
     */
    public Graph (int size) {
        vertices = new ArrayList<> ();
        edges = new ArrayList<> ();
        for (int i = 0; i < size; i++)
            vertices.add (new Vertex<> (i));
    }

    /**
     * Add a new undirected edge without data between two vertices to the graph
     *
     * @param from the id of the first vertice
     * @param to   the id of the second vertice
     */
    public void addEdge (int from, int to) {
        addEdge (from, to, null);
    }

    /**
     * Add a new undirected edge with data between two vertices to the graph
     *
     * @param from the id of the first vertice
     * @param to   the id of the second vertice
     * @param data the data the edge contains
     */
    public void addEdge (int from, int to, E data) {
        Vertex<E> fromVertex = vertices.get (from);
        Vertex<E> toVertex = vertices.get (to);
        Edge<E> edge = new Edge<> (fromVertex, toVertex, data);
        edges.add (edge);
        fromVertex.addEdge (edge);
        toVertex.addEdge (edge);
    }

    /**
     * Add a new directed edge with data between two vertices to the graph
     *
     * @param from the id of the first vertice
     * @param to   the id of the second vertice
     * @param data the data the edge contains
     */
    public Edge<E> addDirectedEdge (int from, int to, E data) {
        Vertex<E> fromVertex = vertices.get (from);
        Vertex<E> toVertex = vertices.get (to);
        Edge<E> edge = new Edge<> (fromVertex, toVertex, data);
        edges.add (edge);
        fromVertex.addEdge (edge);
        toVertex.incrementInDegree ();
        return edge;
    }

    /**
     * Add a new directed edge without data between two vertices to the graph
     *
     * @param from the id of the first vertice
     * @param to   the id of the second vertice
     */
    public void addDirectedEdge (int from, int to) {
        addDirectedEdge (from, to, null);
    }

    /**
     * Returns an unmodifiable view of all edges in the graph.
     *
     * @return an unmodifiable view of all edges in the graph
     */
    public List<Edge<E>> getEdges () {
        return Collections.unmodifiableList (edges);
    }

    /**
     * Returns an unmodifiable view of all vertices in the graph.
     *
     * @return an unmodifiable view of all vertices in the graph
     */
    public List<Vertex<E>> getVertices () {
        return Collections.unmodifiableList (vertices);
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
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
