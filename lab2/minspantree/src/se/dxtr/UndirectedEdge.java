package se.dxtr;

import java.util.Objects;

/**
 * Created by dexter on 05/10/15.
 */
public class UndirectedEdge<T extends Comparable<T>> {
    private final Vertex<T> vertexA;
    private final Vertex<T> vertexB;

    public UndirectedEdge (Vertex<T> vertexA, Vertex<T> vertexB) {
        if (vertexA.compareTo (vertexB) <= 0) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;
        } else {
            this.vertexA = vertexB;
            this.vertexB = vertexA;
        }
    }

    public Vertex<T> getVertexA () {
        return vertexA;
    }

    public Vertex<T> getVertexB () {
        return vertexB;
    }

    public boolean containsVertex (Vertex<T> vertex) {
        if (vertexA.equals (vertex) || vertexB.equals (vertex))
            return true;
        return false;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o)
            return true;
        if (o == null || getClass () != o.getClass ())
            return false;
        UndirectedEdge other = (UndirectedEdge) o;
        return Objects.equals (vertexA, other.vertexA) &&
                Objects.equals (vertexB, other.vertexB);
    }

    @Override
    public int hashCode () {
        return Objects.hash (vertexA, vertexB);
    }
}
