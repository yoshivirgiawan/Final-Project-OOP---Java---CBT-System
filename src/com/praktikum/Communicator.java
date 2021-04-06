package com.praktikum;

import javax.swing.*;
import java.util.ArrayList;

public class Communicator {
    private LoginView loginView;
    private RegisterView registerView;
    private AdminView adminView;
    private InputSoalView inputSoalView;
    private CBTView cbtView;
    private ResultView resultView;
    private StartView startView;
    private DatabaseController databaseController;

    public Communicator() {
        this.databaseController = new DatabaseController();
        this.loginView = new LoginView(this);
        this.registerView = new RegisterView(this);
        this.adminView = new AdminView(this);
        this.inputSoalView = new InputSoalView(this);
        this.cbtView = new CBTView(this);
        this.resultView = new ResultView(this);
        this.startView = new StartView(this);
        goToLoginView();
    }

    public void goToLoginView() {
        this.loginView.setVisible(true);
        this.adminView.setVisible(false);
        this.registerView.setVisible(false);
        this.resultView.setVisible(false);
        this.startView.setVisible(false);
    }

    public void goToRegisterView() {
        this.registerView.setVisible(true);
        this.adminView.setVisible(false);
        this.loginView.setVisible(false);
    }

    public void goToAdminView() {
        adminView.setData();
        this.adminView.setVisible(true);
        this.inputSoalView.setVisible(false);
        this.registerView.setVisible(false);
        this.loginView.setVisible(false);
    }

    public void goToShowDetailSoalView(String title, String buttonTitle) {
        this.inputSoalView.resetData();
        this.inputSoalView.setData(title, buttonTitle);
        this.inputSoalView.setVisible(true);
        this.adminView.setVisible(false);
        this.registerView.setVisible(false);
        this.loginView.setVisible(false);
    }

    public void goToShowDetailSoalView(int index, String title, String buttonTitle) {
        this.inputSoalView.setVisible(true);
        this.inputSoalView.setData(index, title, buttonTitle);
        this.adminView.setVisible(false);
        this.registerView.setVisible(false);
        this.loginView.setVisible(false);
    }

    public void goToStartView() {
        this.startView.setData(databaseController.loginStatus.getUserLogin());
        this.startView.setVisible(true);
        this.loginView.setVisible(false);
    }

    public void goToCBTView() {
        this.cbtView.setDataDefault();
        this.cbtView.setArraySoal();
        this.cbtView.setJawabNull();
        this.cbtView.setArrayJawaban();
        this.cbtView.setKunciJawaban();
        this.cbtView.setData(0);
        this.cbtView.setSelectedRadio();
        this.cbtView.setDataButton();
        this.cbtView.setVisible(true);
        this.inputSoalView.setVisible(false);
        this.adminView.setVisible(false);
        this.registerView.setVisible(false);
        this.loginView.setVisible(false);
        this.startView.setVisible(false);
    }

    public void goToResultView(String[] jawaban, String[] kunciJawaban) {
        this.resultView.setHasilJawaban(jawaban, kunciJawaban);
        this.resultView.setPesanLabel();
        this.resultView.setData();
        this.resultView.setVisible(true);
        this.cbtView.setVisible(false);
    }

    public void goToMainView(String role) {
        if (role.equals("Dosen")) {
            goToAdminView();
        } else if (role.equals("Mahasiswa")) {
            goToStartView();
        }
    }

    public void loginVerify(String username, String password) {
        if (this.databaseController.loginVerify(username, password)) {
            goToMainView(this.databaseController.loginStatus.getUserLogin().getRole());
        } else {
            JOptionPane.showMessageDialog(null, "Periksa kembali username dan password anda");
        }
    }

    public void registerAccount(User user) {
        try {
            if (this.databaseController.registerVerify(user.getUsername())) {
                this.databaseController.getDataUser().add(user);
                JOptionPane.showMessageDialog(null, "Pendaftaran akun anda berhasil");
                goToLoginView();
            } else {
                JOptionPane.showMessageDialog(null, "Username telah digunakan, coba ganti username lain");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pendaftaran akun anda gagal");
        }
    }

    public void logOutAccount() {
        databaseController.loginStatus.logOut();
        goToLoginView();
    }

    public void addSoal(Soal soal) {
        databaseController.addSoal(soal);
    }

    public void updateSoal(int index, Soal soal) {
        databaseController.updateSoal(index, soal);
    }

    public void deleteSoal(int index) {
        databaseController.deleteSoal(index);
    }

    public ArrayList<Soal> getDataSoal() {
        return databaseController.getDataSoal();
    }
}
