package se.dxtr.numbertheorylibrary;

import java.util.Objects;

/**
 * Created by dexter on 12/11/15.
 */
public class RationalNumber implements Comparable<RationalNumber> {
    final public long numerator;
    final public long denominator;

    public RationalNumber(long numerator, long denominator) {
        if (denominator < 0 && numerator < 0) {
            this.numerator = Math.abs(numerator);
        } else if (denominator < 0) {
            this.numerator = -numerator;
        } else {
            this.numerator = numerator;
        }
        this.denominator = Math.abs(denominator);
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
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
    public int compareTo(RationalNumber other) {
        if (denominator != other.denominator)
            return Long.compare(other.denominator, denominator);
        return Long.compare(numerator, other.numerator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RationalNumber that = (RationalNumber) o;
        return numerator == that.numerator &&
                denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return "RationalNumber{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
