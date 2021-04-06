package com.praktikum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JTextField nameInput;
    private JButton registerButton;
    private JButton loginButton;
    private JPanel registerPanel;
    private JComboBox roleSelect;

    public RegisterView(Communicator communicator) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registerPanel);
        this.pack();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.registerAccount(new User(usernameInput.getText(), passwordInput.getText(), nameInput.getText(), roleSelect.getSelectedItem().toString()));
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.goToLoginView();
            }
        });
    }
}
