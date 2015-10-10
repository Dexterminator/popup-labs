package se.dxtr.graphlibrary;

import java.util.Objects;

/**
 * Generic edge class representing an edge between two vertices, as well as optional data associated with the edge.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Edge<E> {
    private final Vertex<E> from;
    private final Vertex<E> to;
    private final E data;

    /**
     * Create an edge between two vertices.
     *
     * @param from the first vertex in the edge
     * @param to   the second vertice in the edge
     * @param data the data associated with the edge
     */
    public Edge (Vertex<E> from, Vertex<E> to, E data) {
        this.from = from;
        this.to = to;
        this.data = data;
    }

    /**
     * Create an edge between two vertices.
     *
     * @param from the first vertex in the edge
     * @param to   the second vertice in the edge
     */
    public Edge (Vertex<E> from, Vertex<E> to) {
        this.from = from;
        this.to = to;
        this.data = null;
    }

    /**
     * Returns the first vertex in the edge.
     * @return the first vertex in the edge
     */
    public Vertex<E> getFrom () {
        return from;
    }

    /**
     * Returns the second vertex in the edge.
     * @return the second vertex in the edge
     */
    public Vertex<E> getTo () {
        return to;
    }

    /**
     * Returns the data associated with the edge or null if no data is associated with this edge.
     * @return the data associated with the edge
     */
    public E getData () {
        return data;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals (from.getId (), edge.from.getId ()) &&
                Objects.equals (to.getId (), edge.to.getId ()) &&
                Objects.equals (data, edge.data);
    }

    @Override
    public int hashCode () {
        return Objects.hash (from, to, data);
    }

    @Override
    public String toString () {
        return "Edge{" +
                "from=" + from.getId () +
                ", to=" + to.getId () +
                ", data=" + data +
                '}';
    }
}
