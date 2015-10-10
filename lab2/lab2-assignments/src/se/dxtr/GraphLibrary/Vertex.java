package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Generic vertex class representing a vertex in a graph with edges associated with some data.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Vertex<E> {
    private final int id;
    private final List<Edge<E>> edges;
    private int inDegree;


    /**
     * Create a vertex with an id.
     *
     * @param id the unique id of the vertex within the graph
     */
    public Vertex (int id) {
        this.id = id;
        edges = new ArrayList<> ();
        inDegree = 0;
    }

    /**
     * Returns the unique id of the vertex.
     *
     * @return the unique id of the vertex
     */
    public int getId () {
        return id;
    }

    /**
     * Returns an unmodifiable view of all edges from the vertex.
     *
     * @return an unmodifiable view of all edges from the vertex
     */
    public List<Edge<E>> getEdges () {
        return Collections.unmodifiableList (edges);
    }

    /**
     * Adds the specified edge to the list of edges that originate in the vertex.
     *
     * @param edge the edge to add to the list of edges that originate in the vertex
     */
    public void addEdge (Edge<E> edge) {
        edges.add (edge);
    }

    /**
     * Returns the number of edges from this vertex to other vertices i.e its degree.
     *
     * @return the number of edges from this vertex to other vertices i.e its degree.
     */
    public int degree () {
        return edges.size ();
    }

    /**
     * Increment the number of vertices with edges to this vertex.
     */
    public void incrementInDegree () {
        inDegree++;
    }

    /**
     * Returns the number of edges from other vertices to this vertex.
     *
     * @return the number of edges from other vertices to this vertex
     */
    public int inDegree () {
        return inDegree;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals (id, vertex.id);
    }

    @Override
    public int hashCode () {
        return Objects.hash (id);
    }

    @Override
    public String toString () {
        return "Vertex{" +
                "id=" + id +
                ", edges=" + edges +
                '}';
    }
}
