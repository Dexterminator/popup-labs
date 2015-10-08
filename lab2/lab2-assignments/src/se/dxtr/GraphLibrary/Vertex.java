package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Created by dexter on 05/10/15.
 */
public class Vertex<E> {
    private final int id;
    private final List<Edge<E>> edges;

    public Vertex (int id) {
        this.id = id;
        edges = new ArrayList<> ();
    }

    public int getId () {
        return id;
    }

    public List<Edge<E>> getEdges () {
        return Collections.unmodifiableList (edges);
    }

    public Vertex<E> getNeighbor (int index) {
        return getOther (edges.get (index));
    }

    private Vertex<E> getOther (Edge<E> edge) {
        if (edge.getTo ().getId () == id)
            return edge.getFrom ();
        return edge.getTo ();
    }

    public void removeEdge (Vertex other) {
        Iterator<Edge<E>> i = edges.iterator ();
        boolean removed = false;
        while (i.hasNext () && !removed) {
            Edge<E> edge = i.next ();
            if (edge.getFrom ().getId () == other.getId () || edge.getTo ().getId () == other.getId ()) {
                i.remove ();
                removed = true;
            }
        }
    }

    public void addEdge (Edge<E> edge) {
        edges.add (edge);
    }

    public int degree () {
        return edges.size ();
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
