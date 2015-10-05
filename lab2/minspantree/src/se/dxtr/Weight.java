package se.dxtr;

public class Weight {
    public final int weight;

    public Weight (int weight) {
        this.weight = weight;
    }

    @Override
    public String toString () {
        return "Weight{" +
                "weight=" + weight +
                '}';
    }
}
