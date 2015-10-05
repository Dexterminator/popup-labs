package se.dxtr;

import java.util.*;

/**
 * Created by dexter on 05/10/15.
 */
public class UndirectedGraph<T extends Comparable<T>> implements Graph<T> {
    private Map<Vertex<T>, Set<Vertex<T>>> neighbors;
    private Set<UndirectedEdge<T>> edges;
    private Map<UndirectedEdge<T>, Integer> weights;
    private int cost = 0;

    public UndirectedGraph () {
        neighbors = new HashMap<> ();
        edges = new HashSet<> ();
        weights = new HashMap<> ();
    }

    @Override
    public boolean adjacent (Vertex<T> vertexA, Vertex<T> vertexB) {
        validateVertices (vertexA, vertexB);
        return neighbors.get (vertexA).contains (vertexB);
    }

    @Override
    public Set<Vertex<T>> getNeighbors (Vertex<T> vertex) {
        validateVertex (vertex);
        return Collections.unmodifiableSet (neighbors.get (vertex));
    }

    @Override
    public boolean addVertex (Vertex<T> vertex) {
        if (!graphContains (vertex)) {
            neighbors.put (vertex, new HashSet<> ());
            return true;
        }

        return false;
    }

    @Override
    public boolean removeVertex (Vertex<T> vertex) {
        if (!graphContains (vertex))
            return false;

        edges.removeIf (edge -> edge.containsVertex (vertex));
        return true;
    }

    @Override
    public boolean addEdge (Vertex<T> vertexA, Vertex<T> vertexB) {
        validateVertices (vertexA, vertexB);
        UndirectedEdge<T> edge = new UndirectedEdge<> (vertexA, vertexB);
        return addEdge (edge);
    }

    public boolean addEdge (Vertex<T> vertexA, Vertex<T> vertexB, int weight) {
        validateVertices (vertexA, vertexB);
        UndirectedEdge<T> edge = new UndirectedEdge<> (vertexA, vertexB);
        return addEdge (edge, weight);
    }

    public boolean addEdge (UndirectedEdge<T> edge, int weight) {
        addWeight (weight, edge);
        return addEdge (edge);
    }

    public boolean addEdge (UndirectedEdge<T> edge) {
        if (edges.contains (edge))
            return false;
        neighbors.get (edge.getVertexA ()).add (edge.getVertexB ());
        neighbors.get (edge.getVertexB ()).add (edge.getVertexA ());
        edges.add (edge);
        return true;
    }

    @Override
    public boolean removeEdge (Vertex<T> vertexA, Vertex<T> vertexB) {
        validateVertices (vertexA, vertexB);
        UndirectedEdge<T> edge = new UndirectedEdge<> (vertexA, vertexB);
        if (!edges.contains (edge))
            return false;

        cost -= weights.remove (edge);
        edges.remove (edge);
        neighbors.get (vertexA).remove (vertexB);
        neighbors.get (vertexB).remove (vertexA);
        return true;
    }

    public int getWeight (Vertex<T> vertexA, Vertex<T> vertexB) {
        validateVertices (vertexA, vertexB);
        UndirectedEdge<T> edge = new UndirectedEdge<> (vertexA, vertexB);
        return getWeight (edge);
    }

    public int getWeight (UndirectedEdge<T> edge) {
        validateEdge (edge);
        return weights.get (edge);
    }

    public Set<UndirectedEdge<T>> getEdges () {
        return Collections.unmodifiableSet (edges);
    }

    public Set<Vertex<T>> getVertices () {
        return Collections.unmodifiableSet (neighbors.keySet ());
    }

    public int getCost () {
        return cost;
    }

    private void validateEdge (UndirectedEdge<T> edge) {
        if (!edges.contains (edge)) {
            throw new NoSuchElementException ("There is no edge between " + edge.getVertexA ().getId () + " and " +
                    edge.getVertexB ().getId ());
        }
    }

    private void validateVertex (Vertex<T> vertex) {
        if (!graphContains (vertex))
            throw new NoSuchElementException ("Graph does not contain vertex: " + vertex.getId ());
    }

    private void validateVertices (Vertex<T> vertexA, Vertex<T> vertexB) {
        validateVertex (vertexA);
        validateVertex (vertexB);
    }

    private void addWeight (int weight, UndirectedEdge<T> edge) {
        weights.put (edge, weight);
        cost += weight;
    }

    private boolean graphContains (Vertex<T> vertex) {
        return neighbors.containsKey (vertex);
    }

    @Override
    public String toString () {
        return "UndirectedGraph{" +
                "edges=" + edges +
                '}';
    }
}