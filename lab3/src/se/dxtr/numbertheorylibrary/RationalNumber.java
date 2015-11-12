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
        long numerator1 = denominator * other.numerator;
        long numerator2 = numerator * other.denominator;
        long newNumerator = numerator1 + numerator2;
        long newDenominator = denominator * other.denominator;
        return shortestTerms(newNumerator, newDenominator);
    }

    public RationalNumber subtract(RationalNumber other) {
        long numerator1 = denominator * other.numerator;
        long numerator2 = numerator * other.denominator;
        long newNumerator = numerator2 - numerator1;
        long newDenominator = denominator * other.denominator;
        return shortestTerms(newNumerator, newDenominator);
    }

    public RationalNumber multiply(RationalNumber other) {
        long newNumerator = numerator * other.numerator;
        long newDenominator = denominator * other.denominator;
        return shortestTerms(newNumerator, newDenominator);
    }

    public RationalNumber divide(RationalNumber other) {
        return multiply(other.inverse());
    }

    public RationalNumber inverse() {
        return new RationalNumber(denominator, numerator);
    }

    private RationalNumber shortestTerms(long numerator, long denominator) {
        long gcd = Math.abs(gcd(Math.abs(numerator), Math.abs(denominator)));
        return new RationalNumber(numerator / gcd, denominator / gcd);
    }

    private long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    @Override
    public String toString() {
        return "RationalNumber{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
