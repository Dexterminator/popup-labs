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
            long x1 = io.getLong();
            long y1 = io.getLong();
            String op = io.getWord();
            long x2 = io.getLong();
            long y2 = io.getLong();
            RationalNumber res = performOperation(new RationalNumber(x1, y1), new RationalNumber(x2, y2), op);
            io.println(res.numerator + " / " + res.denominator);
        }
        io.close();
    }

    private static RationalNumber performOperation(RationalNumber num1, RationalNumber num2, String op) {
        switch (op) {
            case "+":
                return num1.add(num2);
            case "-":
                return num1.subtract(num2);
            case "*":
                return num1.multiply(num2);
            case "/":
                return num1.divide(num2);
            default:
                return null;
        }
    }
}
