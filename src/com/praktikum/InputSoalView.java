package com.praktikum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputSoalView extends JFrame {
    private JPanel showDetailSoalPanel;
    private JLabel labelJudul;
    private JTextArea questionField;
    private JTextField firstAnswer;
    private JTextField secondAnswer;
    private JTextField thirdAnswer;
    private JTextField fourthAnswer;
    private JButton showDetailButton;
    private JButton backButton;
    private JComboBox keyAnswer;
    private JButton hapusButton;
    private Communicator communicator;
    private int index;

    public InputSoalView(Communicator communicator) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(showDetailSoalPanel);
        setPreferredSize(new Dimension(600, 500));
        this.pack();

        this.communicator = communicator;

        showDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showDetailButton.getText().equals("Ubah Soal")) {
                    try {
                        communicator.updateSoal(index, new Soal(questionField.getText(), new Choice[]{new Choice("a", firstAnswer.getText()), new Choice("b", secondAnswer.getText()), new Choice("c", thirdAnswer.getText()), new Choice("d", fourthAnswer.getText())}, keyAnswer.getSelectedItem().toString()));
                        JOptionPane.showMessageDialog(null, "Soal berhasil diubah");
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "Soal gagal diubah");
                    }

                } else if (showDetailButton.getText().equals("Buat Soal")) {
                    try {
                        communicator.addSoal(new Soal(questionField.getText(), new Choice[]{new Choice("a", firstAnswer.getText()), new Choice("b", secondAnswer.getText()), new Choice("c", thirdAnswer.getText()), new Choice("d", fourthAnswer.getText())}, keyAnswer.getSelectedItem().toString()));
                        JOptionPane.showMessageDialog(null, "Soal berhasil dibuat");
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "Soal gagal dibuat");
                    }

                }
                System.out.println(questionField.getText());
                communicator.goToAdminView();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.goToAdminView();
            }
        });
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus soal ini?");
                if (input == JOptionPane.YES_OPTION) {
                    try {
                        communicator.deleteSoal(index);
                        JOptionPane.showMessageDialog(null, "Soal berhasil dihapus");
                        communicator.goToAdminView();
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "Soal gagal dihapus");
                    }
                }
            }
        });
    }

    public void resetData() {
        questionField.setText("");
        firstAnswer.setText("");
        secondAnswer.setText("");
        thirdAnswer.setText("");
        fourthAnswer.setText("");
    }

    public void setData(String title, String buttonTitle) {
        labelJudul.setText(title);
        hapusButton.setVisible(false);
        keyAnswer.setSelectedItem("a");
        showDetailButton.setText(buttonTitle);
    }

    public void setData(int index, String title, String buttonTitle) {
        labelJudul.setText(title);
        this.index = index;
        questionField.setText(communicator.getDataSoal().get(index).getPertanyaan());
        firstAnswer.setText(communicator.getDataSoal().get(index).getMultipleChoice()[0].getJawaban());
        secondAnswer.setText(communicator.getDataSoal().get(index).getMultipleChoice()[1].getJawaban());
        thirdAnswer.setText(communicator.getDataSoal().get(index).getMultipleChoice()[2].getJawaban());
        fourthAnswer.setText(communicator.getDataSoal().get(index).getMultipleChoice()[3].getJawaban());
        keyAnswer.setSelectedItem(communicator.getDataSoal().get(index).getKunciJawaban());
        hapusButton.setVisible(true);
        showDetailButton.setText(buttonTitle);
    }
}
