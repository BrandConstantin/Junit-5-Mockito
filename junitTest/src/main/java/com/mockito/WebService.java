package com.mockito;

public class WebService {
    public void login(String user, String password, Callback callback) {
        if (checkLogin(user, password)) {
            callback.onSuccess("Usuario correcto");
        }
        callback.onFail("Usuario o contraseña incorrecta");
    }

    public boolean checkLogin(String user, String password) {
        if (user.equals("Alberto") && password.equals("pass123")) {
            return true;
        }
        return false;
    }
}
