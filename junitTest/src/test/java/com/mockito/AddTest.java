package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class AddTest {
    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;
    @Captor
    private ArgumentCaptor<Integer> captor;

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

    @Test
    public void addDoubleToIntThenAnswerTest() {
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 7;
            }
        };

        when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
        assertEquals(14, add.addInt(7.7));
    }

    // Arrange - Act - Assert
    @Test
    public void patterTest() {
        // Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(6)).thenReturn(true);
        // Act
        int result = add.add(4, 6);
        // Assert
        assertEquals(10, result);
    }

    // Given - When - Then
    @Test
    public void patterTest2() {
        // Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(6)).willReturn(true);
        // When
        int result = add.add(4, 6);
        // Then
        assertEquals(10, result);
    }

    @Test
    public void argumentMatcherTest() {
        // Given
        given(validNumber.check(anyInt())).willReturn(true);
        // When
        int result = add.add(4, 6);
        // Then
        assertEquals(10, result);
    }

    @Test
    public void addPrintTest() {
        // Given
        given(validNumber.check(anyInt())).willReturn(true);
        given(validNumber.check(anyInt())).willReturn(true);
        // When
        add.addPrint(4, 6);
        // Then
        verify(validNumber).check(4);
        // verify(validNumber, times(2)).check(4); // si se comprueba más de una vez con given añadir times()
        verify(validNumber, never()).check(44); // si no se ha comprobado nunca
        verify(validNumber, atLeast(1)).check(4); // si se ha comprobado al menos una vez
        verify(validNumber, atMost(3)).check(4); // si se ha comprobado como mucho 3 veces

        verify(print).showMessage(10);
        verify(print, never()).showError();
    }

    @Test
    public void captorTest() {
        // Given
        given(validNumber.check(anyInt())).willReturn(true);
        given(validNumber.check(anyInt())).willReturn(true);
        // When
        add.addPrint(4, 6);
        // Then
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(), 10);
    }

    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    public void spyTest() {
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2, spyList.size());
    }

    @Test
    public void mockTest() {
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        // para que no falle, ya que no ha sido mockeado
        // given(mockList.size()).willReturn(2);
        assertEquals(2, mockList.size());
    }
}
