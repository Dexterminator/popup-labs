package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dexter on 05/10/15.
 */
public class Vertex<V, E> {
    private final int id;
    private final List<Edge<V, E>> edges;
    private V data;

    public Vertex (int id, V data) {
        this.id = id;
        edges = new ArrayList<> ();
        this.data = data;
    }

    public Vertex (int id) {
        this.id = id;
        edges = new ArrayList<> ();
        this.data = null;
    }

    public int getId () {
        return id;
    }

    public V getData () {
        return data;
    }

    public void setData (V data) {
        this.data = data;
    }

    public List<Edge> getEdges () {
        return Collections.unmodifiableList (edges);
    }

    public void addEdge (Edge<V, E> edge) {
        edges.add (edge);
    }

    @Override
    public String toString () {
        return "Vertex{" +
                "id=" + id +
                ", edges=" + edges +
                ", data=" + data +
                '}';
    }
}
