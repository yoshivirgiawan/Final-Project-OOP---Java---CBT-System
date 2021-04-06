package com.praktikum;

import java.util.ArrayList;

public class Database {
    private ArrayList<User> dataUser = new ArrayList<>();
    private ArrayList<Soal> dataSoal = new ArrayList<>();

    public Database() {
        this.dataUser.add(new User("admin", "admin", "admin", "Dosen"));
        this.dataUser.add(new User("yoshi", "yoshi", "yoshi", "Mahasiswa"));
//        this.dataSoal.add(new Soal("Berfikir dengan cara yang benar / masuk akal dan sesuai dengan hukum logika disebut?", new Choice[]{new Choice("a", "Berfikir serius"), new Choice("b", "Berfikir logis"), new Choice("c", "Berfikir sistematis"), new Choice("d", "Berkhayal logis")}, "b"));
//        this.dataSoal.add(new Soal("Diagram yang berisikan simbol-simbol untuk menjelaskan urutan langkah logis adalah?", new Choice[]{new Choice("a", "Flowchart"), new Choice("b", "Program"), new Choice("c", "Algoritma"), new Choice("d", "Logika")}, "a"));
//        this.dataSoal.add(new Soal("1+1?", new Choice[]{new Choice("a", "1"), new Choice("b", "2"), new Choice("c", "3"), new Choice("d", "4")}, "b"));
    }

    public ArrayList<User> getDataUser() {
        return dataUser;
    }

    public ArrayList<Soal> getDataSoal() {
        return dataSoal;
    }
}
