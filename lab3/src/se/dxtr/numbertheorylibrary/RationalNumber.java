package se.dxtr.numbertheorylibrary;

import java.util.Objects;

/**
 * Class representing a rational number (numerator/denominator), providing
 * methods for rational number arithmetic.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class RationalNumber implements Comparable<RationalNumber> {
    final public long numerator;
    final public long denominator;

    /**
     * Creates the rational number 'numerator / denominator'.
     */
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

    /**
     * Returns the result of adding {@code other} to this RationalNumber.
     */
    public RationalNumber add(RationalNumber other) {
        long numerator1 = denominator * other.numerator;
        long numerator2 = numerator * other.denominator;
        long newNumerator = numerator1 + numerator2;
        long newDenominator = denominator * other.denominator;
        return shortestTerms(newNumerator, newDenominator);
    }

    /**
     * Returns the result of subtracting {@code other} from this RationalNumber.
     */
    public RationalNumber subtract(RationalNumber other) {
        long numerator1 = denominator * other.numerator;
        long numerator2 = numerator * other.denominator;
        long newNumerator = numerator2 - numerator1;
        long newDenominator = denominator * other.denominator;
        return shortestTerms(newNumerator, newDenominator);
    }

    /**
     * Returns the result of multiplying this rationalnumber with {@code other}.
     */
    public RationalNumber multiply(RationalNumber other) {
        long newNumerator = numerator * other.numerator;
        long newDenominator = denominator * other.denominator;
        return shortestTerms(newNumerator, newDenominator);
    }

    /**
     * Returns the result of dividing this RationalNumber by {@code other}.
     */
    public RationalNumber divide(RationalNumber other) {
        return multiply(other.inverse());
    }

    /**
     * Returns the inverse of this RationalNumber.
     */
    public RationalNumber inverse() {
        return new RationalNumber(denominator, numerator);
    }

    /**
     * Returns this RationNumber expressed in shortest terms.
     */
    private RationalNumber shortestTerms(long numerator, long denominator) {
        long gcd = Math.abs(gcd(Math.abs(numerator), Math.abs(denominator)));
        return new RationalNumber(numerator / gcd, denominator / gcd);
    }

    /**
     * Returns the greatest common divisor of a and b.
     */
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
        return numerator + " / " + denominator;
    }
}
