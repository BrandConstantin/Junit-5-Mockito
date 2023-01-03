package com.mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WebServiceTest {
    private WebService webService;
    @Mock
    private Callback callback;

    @BeforeEach
    void setUp() throws Exception {
        webService = new WebService();
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        webService = null;
    }

    @Test
    void testCheckLogin() {
        assertTrue(webService.checkLogin("Alberto", "pass123"));
    }

    @Test
    void testCheckLoginError() {
        assertFalse(webService.checkLogin("María", "pass123"));
    }

    @Test
    public void loginTest() {
        webService.login("Alberto", "pass123", callback);
        verify(callback).onSuccess("Usuario correcto");
    }

    @Test
    public void loginFailTest() {
        webService.login("Maria", "pass123", callback);
        verify(callback).onFail("Usuario o contraseña incorrecta");
    }
}
