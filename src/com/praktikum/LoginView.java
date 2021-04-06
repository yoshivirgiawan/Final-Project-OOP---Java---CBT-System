package com.praktikum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel loginPanel;
    private JTextField usernameInput;
    private JButton loginButton;
    private JPasswordField passwordInput;
    private JButton registerButton;

    public LoginView(Communicator communicator) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.loginVerify(usernameInput.getText(), passwordInput.getText());
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.goToRegisterView();
            }
        });
    }
}
