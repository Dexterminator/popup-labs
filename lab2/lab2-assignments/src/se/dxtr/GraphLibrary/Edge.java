package se.dxtr.graphlibrary;

/**
 * Created by dexter on 05/10/15.
 */
public class Edge<E> {
    private final Vertex<E> from;
    private final Vertex<E> to;
    private final E data;

    public Edge (Vertex<E> from, Vertex<E> to, E data) {
        this.from = from;
        this.to = to;
        this.data = data;
    }

    public Edge (Vertex<E> from, Vertex<E> to) {
        this.from = from;
        this.to = to;
        this.data = null;
    }

    public Vertex<E> getFrom () {
        return from;
    }

    public Vertex<E> getTo () {
        return to;
    }

    public E getData () {
        return data;
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
