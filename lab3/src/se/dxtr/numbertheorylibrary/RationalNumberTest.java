package se.dxtr.numbertheorylibrary;

import static org.junit.Assert.*;

public class RationalNumberTest {

    @org.junit.Test
    public void testCompareTo() throws Exception {
        RationalNumber num1 = new RationalNumber(2, 5);
        RationalNumber num2 = new RationalNumber(2, 6);
        assertTrue(num1.compareTo(num2) > 0);
        assertTrue(num2.compareTo(num1) < 0);

        num1 = new RationalNumber(-2, 8);
        num2 = new RationalNumber(2, 8);
        assertTrue(num1.compareTo(num2) < 0);
        assertTrue(num2.compareTo(num1) > 0);

        num1 = new RationalNumber(10, 5);
        num2 = new RationalNumber(11, 5);
        assertTrue(num1.compareTo(num2) < 0);
        assertTrue(num2.compareTo(num1) > 0);

        num1 = new RationalNumber(1, 4);
        num2 = new RationalNumber(1, 4);
        assertTrue(num1.compareTo(num2) == 0);
    }
}