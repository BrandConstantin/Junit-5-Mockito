package com.mockito;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidNumberTest {
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp() {
        validNumber = new ValidNumber();
    }

    @AfterEach
    public void tearDown() {
        validNumber = null;
    }

    @Test
    public void checkTest() {
        assertEquals(true, validNumber.check(5));
    }

    @Test
    public void checkNegativeTest() {
        assertEquals(true, validNumber.check(-5));
    }

    @Test
    public void checkStringTest() {
        assertEquals(true, validNumber.check("5"));
    }

    @Test
    public void checkisNotStringTest() {
        assertNotEquals(true, validNumber.check("5"));
    }

    @Test
    public void checkisZeroNegativeTest() {
        assertNotEquals(true, validNumber.checkZero(-55));
    }

    @Test
    public void checkisZeroStringTest() {
        assertNotEquals(false, validNumber.checkZero("5"));
    }

    @Test
    public void checkisZeroTest() {
        assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
    }

    @Test
    public void doubleToIntTest() {
        assertEquals(9, validNumber.doubleToInt(9.99));
    }

    @Test
    public void doubleToIntErrorTest() {
        assertEquals(0, validNumber.doubleToInt("9.99"));
    }
}
