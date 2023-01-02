package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AddTest {
    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp() { // inicializar los mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest() {
        // cuando se ejecuté el método validNumber.check con el parámetro de entrada 3, que devuelva un true
        when(validNumber.check(3)).thenReturn(true);
        boolean checkNumber = validNumber.check(3);
        assertEquals(true, checkNumber);

        when(validNumber.check("un string")).thenReturn(false);
        checkNumber = validNumber.check("un string");
        assertEquals(false, checkNumber);
    }

    @Test
    public void addMockExceptionTest() {
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar un 0"));
        Exception exception = null;
        try {
            validNumber.checkZero(0);
        } catch (ArithmeticException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void addRealMethod() {
        when(validNumber.check(3)).thenCallRealMethod();
        assertEquals(true, validNumber.check(3));
        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(false, validNumber.check("3"));
    }
}
