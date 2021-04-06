package com.praktikum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartView extends JFrame {
    private JLabel namaUser;
    private JButton startButton;
    private JPanel startPanel;
    private JLabel sambutanLabel;
    private JButton logoutButton;
    private Communicator communicator;

    public StartView(Communicator communicator) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(startPanel);
        setPreferredSize(new Dimension(600, 500));
        this.pack();

        this.communicator = communicator;

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.goToCBTView();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.logOutAccount();
            }
        });
    }

    public String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void setData(User user) {
        this.namaUser.setText(capitalize(user.getName()));
        if(this.communicator.getDataSoal().size() == 0) {
            this.sambutanLabel.setText("Soal belum selesai diinputkan, harap menunggu sebentar");
            this.logoutButton.setVisible(true);
            this.startButton.setEnabled(false);
        } else {
            this.sambutanLabel.setText("Silahkan tekan tombol mulai untuk memulai ujiannya");
            this.logoutButton.setVisible(false);
            this.startButton.setEnabled(true);
        }
    }
}
