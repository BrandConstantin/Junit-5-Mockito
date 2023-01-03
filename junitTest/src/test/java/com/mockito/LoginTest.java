package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class LoginTest {
    @InjectMocks
    private Login login;
    @Mock
    private WebService webService;
    @Captor
    private ArgumentCaptor<Callback> callbackArgumCapt;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doLoginTest() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String user = (String) invocation.getArguments()[0];
                String pass = (String) invocation.getArguments()[1];
                Callback callback = (Callback) invocation.getArguments()[2];

                assertEquals("Alberto", user);
                assertEquals("pass123", pass);

                if (user.equals("Alberto") && pass.equals("pass123")) {
                    callback.onSuccess("ok");
                } else {
                    callback.onFail("KO");
                }

                return null;
            }
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertEquals(login.isLogin, true);
    }

    @Test
    void doLoginFailTest() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String user = (String) invocation.getArguments()[0];
                String pass = (String) invocation.getArguments()[1];
                Callback callback = (Callback) invocation.getArguments()[2];

                assertEquals("Alberto", user);
                assertEquals("pass123", pass);

                if (user.equals("María") && pass.equals("pass123")) {
                    callback.onSuccess("ok");
                } else {
                    callback.onFail("KO");
                }

                return null;
            }
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertEquals(login.isLogin, false);
    }

    @Test
    public void doLoginCaptorTest() {
        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), callbackArgumCapt.capture());
        assertEquals(login.isLogin, false);

        Callback callback = callbackArgumCapt.getValue();
        callback.onSuccess("Usuario logueado");
        assertEquals(login.isLogin, true);

        callback.onFail("Usuario o contraseña incorrecta");
        assertEquals(login.isLogin, false);
    }
}
