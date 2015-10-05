package se.dxtr;

import java.util.*;

/**
 * Created by dexter on 05/10/15.
 */
public class UndirectedGraph implements Graph {
    private Map<Vertex, Set<Vertex>> neighbors;
    private Set<UndirectedEdge> edges;
    private Map<UndirectedEdge, Integer> weights;

    public UndirectedGraph () {
        neighbors = new HashMap<> ();
        edges = new HashSet<> ();
        weights = new HashMap<> ();
    }


    @Override
    public boolean adjacent (Vertex vertexA, Vertex vertexB) {
        validateVertices (vertexA, vertexB);
        return neighbors.get (vertexA).contains (vertexB);
    }

    @Override
    public Set<Vertex> neighbors (Vertex vertex) {
        validateVertex (vertex);
        return Collections.unmodifiableSet (neighbors.get (vertex));
    }

    @Override
    public boolean addVertex (Vertex vertex) {
        if (!graphContains (vertex)) {
            neighbors.put (vertex, new HashSet<> ());
            return true;
        }

        return false;
    }

    @Override
    public boolean removeVertex (Vertex vertex) {
        if (!graphContains (vertex))
            return false;

        edges.removeIf (edge -> edge.containsVertex (vertex));
        return true;
    }

    @Override
    public boolean addEdge (Vertex vertexA, Vertex vertexB) {
        validateVertices (vertexA, vertexB);
        UndirectedEdge edge = new UndirectedEdge (vertexA, vertexB);
        if (edges.contains (edge))
            return false;
        neighbors.get (vertexA).add (vertexB);
        neighbors.get (vertexB).add (vertexB);
        edges.add (edge);
        return true;
    }

    @Override
    public boolean removeEdge (Vertex vertexA, Vertex vertexB) {
        validateVertices (vertexA, vertexB);
        UndirectedEdge edge = new UndirectedEdge (vertexA, vertexB);
        if (!edges.contains (edge))
            return false;

        edges.remove (edge);
        neighbors.get (vertexA).remove (vertexB);
        neighbors.get (vertexB).remove (vertexA);
        return true;
    }

    private void validateVertex (Vertex vertex) {
        if (!graphContains (vertex))
            throw new NoSuchElementException ("Graph does not contain vertex: " + vertex.getId ());
    }

    private void validateVertices (Vertex vertexA, Vertex vertexB) {
        validateVertex (vertexA);
        validateVertex (vertexB);
    }

    private boolean graphContains (Vertex vertex) {
        return neighbors.containsKey (vertex);
    }

}
