package se.dxtr.modulararithmetic;

import se.dxtr.numbertheorylibrary.ModularOperations;
import se.dxtr.stringlibrary.Kattio;

public class ModularArithmetic {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            long n = io.getInt();
            int t = io.getInt();
            if (n == 0 && t == 0) {
                break;
            }
            for (int i = 0; i < t; i++) {
                long x = io.getInt();
                String op = io.getWord();
                long y = io.getInt();
                long result = performOperation(x, y, n, op);
                io.println(result);
            }
        }
        io.close();
    }

    private static long performOperation(long x, long y, long n, String op) {
        switch (op) {
            case "+":
                return ModularOperations.add(x, y, n);
            case "-":
                return ModularOperations.subtract(x, y, n);
            case "*":
                return 0;
            case "/":
                return 0;
            default:
                return 0;
        }
    }
}
