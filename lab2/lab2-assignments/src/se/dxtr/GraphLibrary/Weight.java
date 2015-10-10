package se.dxtr.graphlibrary;

/**
 * Container class for an edge weight value.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Weight {
    public final int weight;

    public Weight (int weight) {
        this.weight = weight;
    }

    public int getWeight () {
        return weight;
    }

    @Override
    public String toString () {
        return "Weight{" +
                "weight=" + weight +
                '}';
    }
}
