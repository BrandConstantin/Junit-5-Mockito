package com.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class AddMock_Test2 { // vamos a usar las anotaciones
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
        add.add(3, 2);
        Mockito.verify(validNumber).check(3);
        Mockito.verify(validNumber).check(5); // no funciona porque nunca se ha llamado el método con 5
    }
}
