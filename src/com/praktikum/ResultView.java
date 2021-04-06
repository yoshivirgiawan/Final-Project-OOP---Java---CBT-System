package com.praktikum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultView extends JFrame {
    private JList<String> listHasilJawaban;
    private JButton selesaiButton;
    private JPanel resultPanel;
    private JLabel totalNilai;
    private JLabel pesanLabel;
    private Communicator communicator;
    private String[] hasilJawaban;
    private double benar;
    DefaultListModel<String> listData = new DefaultListModel<>();

    public ResultView(Communicator communicator) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(resultPanel);
        setPreferredSize(new Dimension(600, 500));
        this.pack();

        this.communicator = communicator;

        selesaiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.logOutAccount();
            }
        });
    }

    public void setHasilJawaban(String[] jawaban, String[] kunciJawaban) {
        this.hasilJawaban = new String[this.communicator.getDataSoal().size()];

        for (int i = 0; i < jawaban.length; i++) {
            System.out.println(jawaban[i] + " : " + kunciJawaban[i]);
            if (jawaban[i] == null) {
                this.hasilJawaban[i] = "Belum dijawab";
            } else if (jawaban[i].equals(kunciJawaban[i])) {
                this.hasilJawaban[i] = "Benar";
            } else {
                this.hasilJawaban[i] = "Salah";
            }
        }

        this.benar = 0;

        for (int i = 0; i < jawaban.length; i++) {
            if (this.hasilJawaban[i].equals("Benar")) {
                this.benar ++;
            }
        }

        this.totalNilai.setText(String.format("%.2f", this.benar / jawaban.length * 100));
    }

    public void setPesanLabel() {
        if (Double.parseDouble(totalNilai.getText()) < 70) {
            this.pesanLabel.setText("Jangan patah semangat dan terus belajar");
        } else {
            this.pesanLabel.setText("Selamat!!!");
        }

    }

    public void setData() {
        listData.clear();

        int listIndex = 1;
        for (String hasilJawaban : this.hasilJawaban){
            listData.addElement(listIndex + ". " + hasilJawaban);
            listIndex++;
        }

        listHasilJawaban.setModel(listData);
    }
}
