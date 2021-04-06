package com.praktikum;

public class LoginStatus {
    private User userLogin;

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

    public void logOut() {
        this.userLogin = null;
    }
}
