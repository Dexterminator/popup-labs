package se.dxtr.numbertheorylibrary;

/**
 * Value class containing the solutions x mod k to a chinese remainder problem instance.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class ChineseRemainderSolution {
    public final long x;
    public final long k;

    public ChineseRemainderSolution(long x, long k) {
        this.x = x;
        this.k = k;
    }
}
