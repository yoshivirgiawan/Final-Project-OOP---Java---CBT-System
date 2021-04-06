package com.praktikum;

public class Soal {
    private String pertanyaan;
    private Choice[] multipleChoice;
    private String kunciJawaban;

    public Soal(String pertanyaan, Choice[] multipleChoice, String kunciJawaban) {
        this.pertanyaan = pertanyaan;
        this.multipleChoice = multipleChoice;
        this.kunciJawaban = kunciJawaban;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public Choice[] getMultipleChoice() {
        return multipleChoice;
    }

    public String getKunciJawaban() {
        return kunciJawaban;
    }
}
