package com.mockito;

public class WebService {
    public boolean checkLogin(String user, String password) {
        if (user.equals("Alberto") && password.equals("pass123")) {
            return true;
        }
        return false;
    }
}
