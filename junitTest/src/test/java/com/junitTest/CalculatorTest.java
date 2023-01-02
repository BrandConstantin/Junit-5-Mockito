package com.junitTest;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple Calculator.
 */
public class CalculatorTest {
    private Calculator calculator;
    private Calculator calculatorNull;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Inicialización de la calculadora!");
    }

    @Test
    public void calculatorNotNullTest() {
        // calculator = new Calculator();
        assertNotNull(calculator);
        System.out.println("calculatorNotNullTest");
    }

    @AfterEach
    public void setDown() {
        calculator = null;
        System.out.println("Calculator eliminada!");
    }

    @Test
    public void calculatorNullTest() {
        assertNull(calculatorNull);
        System.out.println("Calculator a null");
    }

    @Test
    public void addNumbers() {
        // 1. setup
        Calculator calculatorAssert = new Calculator();
        int resultadoEsperado = 30;
        int resultadoActual;

        // 2. action
        resultadoActual = calculatorAssert.add(20, 10);

        // 3. assert
        assertEquals(resultadoEsperado, resultadoActual);
        System.out.println("Resultado esperado para addNumber");
    }

    // optimizand test addNumbers
    @Test
    public void addTestNumber() {
        assertEquals(30, calculator.add(10, 20));
        System.out.println("addTestNumber funciona!");
    }

    @Test
    public void assertTypeTest() {
        assertTrue(1 == 1);

        Calculator c1 = new Calculator();
        Calculator c2 = new Calculator();
        Calculator c3 = c2;
        assertNotSame(c1, c2);
        assertSame(c2, c3);

        assertEquals("Domingo", "Domingo");

        assertEquals(1, 1.4, 0.5); // número actual, número esperado, margen de error
        // assertEquals(1, 1.7, 0.5);
    }

    @Test
    public void divideTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Disabled("Deshabilitado hasta nuevo aviso")
    @Test
    public void divideTestWithZeroResult() {
        assertEquals(2, calculator.divide(10, 0));
        fail("Fallo detectado");
    }

    @Test
    @DisplayName("Metodo dividir por cero lanzando una excepción")
    public void divideTestWithZeroResultWithException() {
        assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0));
    }

    @Test
    public void addAssertAllTest() {
        assertAll(() -> assertEquals(30, calculator.add(11, 20)), () -> assertEquals(20, calculator.divide(20, 1)),
                () -> assertEquals(2, calculator.substract(10, 8)));
    }

}
