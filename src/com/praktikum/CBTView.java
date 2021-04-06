package com.praktikum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CBTView extends JFrame {
    private JPanel cbtPanel;
    private JButton selanjutnyaButton;
    private JButton sebelumnyaButton;
    private JRadioButton firstAnswer;
    private JRadioButton secondAnswer;
    private JRadioButton thirdAnswer;
    private JRadioButton fourthAnswer;
    private JTextArea questField;
    private JLabel nomorSoal;
    private Communicator communicator;
    private ArrayList<Soal> soal;
    private int indexSoal;
    private String[] jawaban;
    private String[] abjadJawaban;
    private Soal[] soalMultipleChoice;
    private Choice[][] choices;
    private String[] kunciJawaban;
    private ButtonGroup buttonGroup = new ButtonGroup();
    Random rand = new Random();

    public CBTView(Communicator communicator) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(cbtPanel);
        setPreferredSize(new Dimension(600, 500));
        this.pack();

        buttonGroup.add(firstAnswer);
        buttonGroup.add(secondAnswer);
        buttonGroup.add(thirdAnswer);
        buttonGroup.add(fourthAnswer);

        this.communicator = communicator;

        selanjutnyaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selanjutnyaButton.getText().equals("Selanjutnya")) {
                    setJawaban();
                    setData(indexSoal + 1);
                    setSelectedRadio();
                    setDataButton();
                } else if (selanjutnyaButton.getText().equals("Kumpulkan jawaban")) {
                    setJawaban();
                    if (checkJawabanContainsNull(abjadJawaban)) {
                        int input = JOptionPane.showConfirmDialog(null, "Jawaban anda masih ada yang belum dijawab. Apakah anda yakin untuk mengumpulkan jawaban anda?");
                        if (input == JOptionPane.YES_OPTION) {
                            communicator.goToResultView(abjadJawaban, kunciJawaban);
                        }
                    }
                    else {
                        int input = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk mesubmit jawaban anda?");
                        if (input == JOptionPane.YES_OPTION) {
                            communicator.goToResultView(abjadJawaban, kunciJawaban);
                        }
                    }
                }
            }
        });
        sebelumnyaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setJawaban();
                setData(indexSoal - 1);
                setSelectedRadio();
                setDataButton();
            }
        });
    }

    public boolean checkJawabanContainsNull(String[] jawaban) {
        boolean match = false;
        for (String soal:jawaban) {
            if (soal == null) {
                match = true;
                break;
            }
        }

        return match;
    }

    public void setArraySoal() {
        this.soal = new ArrayList<>(communicator.getDataSoal());
        soalMultipleChoice = soal.toArray(new Soal[communicator.getDataSoal().size()]);

        for (int i = 0; i < soalMultipleChoice.length; i++) {
            int randomIndexToSwap = this.rand.nextInt(soalMultipleChoice.length);
            Soal temp = soalMultipleChoice[randomIndexToSwap];
            soalMultipleChoice[randomIndexToSwap] = soalMultipleChoice[i];
            soalMultipleChoice[i] = temp;
        }
    }

    public void setArrayJawaban() {
        for (int i = 0; i < soalMultipleChoice.length; i++) {
            this.choices[i] = this.soalMultipleChoice[i].getMultipleChoice();
        }

        for (int i = 0; i < choices.length; i++) {
            for (int j = 0; j < 4; j++) {
                int randomIndexToSwap = this.rand.nextInt(choices[i].length);
                Choice temp = choices[i][randomIndexToSwap];
                choices[i][randomIndexToSwap] = choices[i][j];
                choices[i][j] = temp;
            }
        }
    }

    public void setJawabNull() {
        if(jawaban != null) {
            Arrays.fill(jawaban, null);
        }
    }

    public void setKunciJawaban() {
        for (int i = 0; i < soalMultipleChoice.length; i++) {
            this.kunciJawaban[i] = this.soalMultipleChoice[i].getKunciJawaban();
        }
    }

    public void setJawaban() {
        if (firstAnswer.isSelected()) {
            jawaban[indexSoal] = "a";
            abjadJawaban[indexSoal] = soalMultipleChoice[indexSoal].getMultipleChoice()[0].getAbjad();
        } else if (secondAnswer.isSelected()) {
            jawaban[indexSoal] = "b";
            abjadJawaban[indexSoal] = soalMultipleChoice[indexSoal].getMultipleChoice()[1].getAbjad();
        } else if (thirdAnswer.isSelected()) {
            jawaban[indexSoal] = "c";
            abjadJawaban[indexSoal] = soalMultipleChoice[indexSoal].getMultipleChoice()[2].getAbjad();
        } else if (fourthAnswer.isSelected()) {
            jawaban[indexSoal] = "d";
            abjadJawaban[indexSoal] = soalMultipleChoice[indexSoal].getMultipleChoice()[3].getAbjad();
        }
        System.out.println("jawaban[indexSoal] : " + jawaban[indexSoal]);
    }

    public void clearSelection() {
        buttonGroup.clearSelection();
    }

    public void setDataDefault() {
        this.jawaban = new String[communicator.getDataSoal().size()];
        this.abjadJawaban = new String[communicator.getDataSoal().size()];
        this.kunciJawaban = new String[communicator.getDataSoal().size()];
        this.soalMultipleChoice = new Soal[communicator.getDataSoal().size()];
        this.choices = new Choice[communicator.getDataSoal().size()][4];
        System.out.println("database size: " + communicator.getDataSoal().size());
    }

    public void setData(int index) {
        indexSoal = index;
        nomorSoal.setText("Soal No." + (index + 1));
        questField.setText(soalMultipleChoice[index].getPertanyaan());
        firstAnswer.setText("a. " + soalMultipleChoice[index].getMultipleChoice()[0].getJawaban());
        secondAnswer.setText("b. " + soalMultipleChoice[index].getMultipleChoice()[1].getJawaban());
        thirdAnswer.setText("c. " + soalMultipleChoice[index].getMultipleChoice()[2].getJawaban());
        fourthAnswer.setText("d. " + soalMultipleChoice[index].getMultipleChoice()[3].getJawaban());
    }

    public void setSelectedRadio() {
        if(jawaban[indexSoal] != null) {
            if (jawaban[indexSoal].equals("a")) {
                firstAnswer.setSelected(true);
            } else if (jawaban[indexSoal].equals("b")) {
                secondAnswer.setSelected(true);
            } else if (jawaban[indexSoal].equals("c")) {
                thirdAnswer.setSelected(true);
            } else if (jawaban[indexSoal].equals("d")) {
                fourthAnswer.setSelected(true);
            }
        } else if (jawaban[indexSoal] == null) {
            clearSelection();
        }
    }

    public void setDataButton() {
        if (indexSoal == 0) {
            if (communicator.getDataSoal().size() == 1) {
                selanjutnyaButton.setText("Kumpulkan jawaban");
            } else {
                selanjutnyaButton.setText("Selanjutnya");
            }
            sebelumnyaButton.setVisible(false);
        } else if (indexSoal == (communicator.getDataSoal().size() - 1)) {
            selanjutnyaButton.setText("Kumpulkan jawaban");
            sebelumnyaButton.setVisible(true);
        } else {
            sebelumnyaButton.setVisible(true);
            selanjutnyaButton.setText("Selanjutnya");
            selanjutnyaButton.setVisible(true);
        }
    }
}
