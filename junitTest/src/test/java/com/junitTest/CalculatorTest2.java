package com.junitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorTest2 {
    private static Calculator calculatorStatic;

    @Nested
    class addMultipleTest {
        Calculator calculator = new Calculator();

        @Test
        public void addPositiveTest() {
            assertEquals(20, calculator.add(10, 10));
        }

        @Test
        public void addNegativeTest() {
            assertEquals(-20, calculator.add(-30, -10));
        }

        @Test
        public void addDivideTest() {
            assertEquals(1, calculator.divide(10, 10));
        }
    }

    @BeforeAll
    public static void inicializarTodosLosTests() {
        calculatorStatic = new Calculator();
        System.out.println("Calculadora estática inicializada");
    }

    @AfterAll
    public static void finalizarTodosLosTests() {
        calculatorStatic = null;
        System.out.println("Calculadora estática finalizada");
    }

    @Test
    public void CalculatorNotNullTest() {
        assertNotNull(calculatorStatic);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, suma={2}")
    @MethodSource("addProviderData")
    public void addParametrizedTest(int a, int b, int suma) {
        assertEquals(suma, calculatorStatic.add(a, b));
    }

    private static Stream<Arguments> addProviderData() {
        return Stream.of(Arguments.of(6, 2, 8), Arguments.of(-6, -2, -8), Arguments.of(6, -2, 4),
                Arguments.of(-6, 2, -4), Arguments.of(6, 0, 6));
    }

    @Test
    public void timeOutTest() {
        assertTimeout(Duration.ofMillis(500), () -> {
            calculatorStatic.longTaskOperation();
        });
    }
}
