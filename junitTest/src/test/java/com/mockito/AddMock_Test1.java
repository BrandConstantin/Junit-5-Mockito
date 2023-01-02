package com.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AddMock_Test1 {
    private Add add;
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp() {
        // validNumber = new ValidNumber(); // de este modo se pasa un objeto real
        // add = new Add(validNumber);

        validNumber = Mockito.mock(ValidNumber.class);
        add = new Add(validNumber);
    }

    @Test
    public void addTest() {
        add.add(3, 2);
        Mockito.verify(validNumber).check(3);
        Mockito.verify(validNumber).check(5); // no funciona porque nunca se ha llamado el método con 5
    }
}
