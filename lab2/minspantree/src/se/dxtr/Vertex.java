package se.dxtr;

import java.util.Objects;

/**
 * Created by dexter on 05/10/15.
 */
public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {
    private final T id;

    public Vertex (T id) {
        this.id = id;
    }

    public T getId () {
        return id;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o)
            return true;
        if (o == null || getClass () != o.getClass ())
            return false;
        Vertex other = (Vertex) o;
        return Objects.equals (id, other.id);
    }

    @Override
    public int hashCode () {
        return Objects.hash (id);
    }

    @Override
    public int compareTo (Vertex<T> o) {
        return id.compareTo (o.getId ());
    }
}
