package se.dxtr.chineseremainder;

import se.dxtr.numbertheorylibrary.ChineseRemainderSolution;
import se.dxtr.numbertheorylibrary.ChineseRemainders;
import se.dxtr.stringlibrary.Kattio;

/**
 * Solve the chineseremainder kattis problem.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class ChineseRemainder {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int t = io.getInt();
        for (int i = 0; i < t; i++) {
            int a = io.getInt();
            int n = io.getInt();
            int b = io.getInt();
            int m = io.getInt();
            ChineseRemainderSolution solution = ChineseRemainders.solveCoprime(a, n, b, m);
            io.println(solution.x + " " + solution.k);
        }
        io.close();
    }
}
