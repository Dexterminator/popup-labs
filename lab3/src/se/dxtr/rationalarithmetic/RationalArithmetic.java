package se.dxtr.rationalarithmetic;

import se.dxtr.numbertheorylibrary.RationalNumber;
import se.dxtr.stringlibrary.Kattio;

/**
 * Created by dexter on 12/11/15.
 */
public class RationalArithmetic {

    static Kattio io = new Kattio(System.in, System.out);
    public static void main(String[] args) {
        int operations = io.getInt();
        for (int i = 0; i < operations; i++) {
            int x1 = io.getInt();
            int y1 = io.getInt();
            String op = io.getWord();
            int x2 = io.getInt();
            int y2 = io.getInt();
            RationalNumber res = performOperation(new RationalNumber(x1, y1), new RationalNumber(x2, y2), op);
            io.println(res.numerator + " / " + res.denominator);
        }
        io.close();
    }

    private static RationalNumber performOperation(RationalNumber num1, RationalNumber num2, String op) {
        switch (op) {
            case "+":
                return num1.add(num2);
            default:
                return null;
        }
    }
}
