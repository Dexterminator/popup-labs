package se.dxtr.numbertheorylibrary;

/**
 * Created by dexter on 12/11/15.
 */
public class RationalNumber {
    final public long numerator;
    final public long denominator;

    public RationalNumber(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public RationalNumber add(RationalNumber other) {
        long newDenominator = denominator * other.denominator;
        long numerator1 = denominator * other.numerator;
        long numerator2 = numerator * other.denominator;
        long newNumerator = numerator1 + numerator2;
        return shortestTerms(newNumerator, newDenominator);
    }

    private RationalNumber shortestTerms(long numerator, long denominator) {
        long gcd = gcd(numerator, denominator);
        return new RationalNumber(numerator / gcd, denominator / gcd);
    }

    private long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
