/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.gui;

import com.control_notas.daoimpl.AuthenticationFilter;
import com.control_notas.daoimpl.FilterManager;
import com.control_notas.daoimpl.Target;
import com.control_notas.model.Usuario;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class jFrameLogin extends JFrame implements ActionListener{
    
    //Attributes
    Container container = getContentPane();
    JLabel userLabel = new JLabel("Correo");
    JLabel passwordLabel = new JLabel("Contraseña");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JCheckBox showPassword = new JCheckBox("mostrar Contraseña");
    
    //Constructor
    public jFrameLogin () {
        initLoginFrame();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }
    
    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }
    
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText;
        String pwdText;
        userText = userTextField.getText();
        pwdText = passwordField.getText();

        //Verify JFrame Control Access
        if (e.getSource().equals(resetButton)) {
            clearJTextField();

        }

        if (e.getSource().equals(showPassword)) {
            visibilityPassword();
        }

        if (e.getSource().equals(loginButton)) {
            //Filter
            FilterManager filterManager = new FilterManager(new Target());
            filterManager.setFilter(new AuthenticationFilter());
        
            Usuario _user = new Usuario();

            //Apply Filter
            _user.setFilterManager(filterManager);
            try {
                //Verify Access Credentials
                if (_user.sendCredentials(userText, pwdText)) {
                    clearJTextField();
                    this.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
                
                //TERMINAR IMPLEMENTACION DE INTERCEPT
            } catch (Exception ex) {
                Logger.getLogger(jFrameLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
     private String getRelativeRute () {
        String ruta = ".\\src\\com\\control_notas\\gui\\images\\icon_application.png";
        return ruta;
    }
    private void clearJTextField() 
    {
        userTextField.setText("");
        passwordField.setText("");
    }
    
    private void visibilityPassword()
    {
        if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
    }
    
    public void initLoginFrame()
    {
        String url = getRelativeRute();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setBounds(10, 10, 370, 600);
        this.setTitle("Login Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
