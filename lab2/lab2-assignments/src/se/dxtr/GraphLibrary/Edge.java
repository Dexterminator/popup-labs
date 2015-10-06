package se.dxtr.graphlibrary;

/**
 * Created by dexter on 05/10/15.
 */
public class Edge<V, E> {
    private final Vertex<V, E> from;
    private final Vertex<V, E> to;
    private final E data;

    public Edge (Vertex<V, E> from, Vertex<V, E> to, E data) {
        this.from = from;
        this.to = to;
        this.data = data;
    }

    public Edge (Vertex<V, E> from, Vertex<V, E> to) {
        this.from = from;
        this.to = to;
        this.data = null;
    }

    public Vertex<V, E> getFrom () {
        return from;
    }

    public Vertex<V, E> getTo () {
        return to;
    }

    public E getData () {
        return data;
    }

    @Override
    public String toString () {
        return "Edge{" +
                "from=" + from.getId () + ": " + from.getData () +
                ", to=" + to.getId () + ": " + to.getData () +
                ", data=" + data +
                '}';
    }
}
