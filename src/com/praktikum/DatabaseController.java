package com.praktikum;

import java.util.ArrayList;

public class DatabaseController {
    private Database database;
    public LoginStatus loginStatus;

    public DatabaseController() {
        database = new Database();
        loginStatus = new LoginStatus();
    }

    public boolean loginVerify(String username, String password) {
        boolean match = false;

        for (User loginUser : this.database.getDataUser()) {
            if (loginUser.getUsername().equals(username) && loginUser.getPassword().equals(password)) {
                loginStatus.setUserLogin(loginUser);
                match = true;
            }
        }

        return match;
    }

    public boolean registerVerify(String username) {
        boolean match = true;

        for (User registerUser : database.getDataUser()) {
            if (registerUser.getUsername().equals(username)) {
                match = false;
            }
        }

        return match;
    }

    public void addSoal(Soal soal) {
        database.getDataSoal().add(soal);
    }

    public void updateSoal(int index, Soal soal) {
        database.getDataSoal().set(index, soal);
    }

    public void deleteSoal(int index) {
        database.getDataSoal().remove(index);
    }

    public ArrayList<User> getDataUser() {
        return database.getDataUser();
    }

    public ArrayList<Soal> getDataSoal() {
        return database.getDataSoal();
    }
}
