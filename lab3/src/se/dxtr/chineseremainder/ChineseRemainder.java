package se.dxtr.chineseremainder;

import se.dxtr.stringlibrary.ChineseRemainderSolution;
import se.dxtr.stringlibrary.ChineseRemainders;
import se.dxtr.stringlibrary.Kattio;

/**
 * Created by dexter on 12/11/15.
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
