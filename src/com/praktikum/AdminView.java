package com.praktikum;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private JButton buatSoalButton;
    private JButton logoutButton;
    private JList<String> listSoal;
    private JPanel adminPanel;
    Communicator communicator;
    DefaultListModel<String> listData = new DefaultListModel<>();

    public AdminView(Communicator communicator) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(adminPanel);
        setPreferredSize(new Dimension(600, 500));
        this.pack();

        this.listSoal.setSelectionMode(0);

        this.communicator = communicator;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.logOutAccount();
            }
        });
        buatSoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.goToShowDetailSoalView("Buat Soal", "Buat Soal");
            }
        });
        listSoal.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listSoal.getSelectedIndex() != -1) {
                    communicator.goToShowDetailSoalView(listSoal.getSelectedIndex(), "Detail Soal", "Ubah Soal");
                }
            }
        });
    }

    public void setData() {
        listData.clear();

        int listIndex = 1;
        for (Soal dataTaskList : this.communicator.getDataSoal()){
            listData.addElement(listIndex + ". " + dataTaskList.getPertanyaan());
            listIndex++;
        }

        listSoal.setModel(listData);
    }
}
