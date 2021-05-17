package com.control_notas.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class jPanelRefInformation extends JPanel{
    
    private JLabel nameLabel;
    private JTextField nameTextField;

    //Constructor
    public jPanelRefInformation(JLabel nameLabel, JTextField nameTextField) {
        this.inicializarRecursos(nameLabel, nameTextField);
        this.inicializarEntorno();
    }
    
    //Getters and Setters

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }
    
    private void inicializarRecursos(JLabel nameLabel, JTextField nameTextField){
        this.nameLabel = nameLabel;
        this.nameLabel.setForeground(Color.WHITE);
        this.nameLabel.setText(this.nameLabel.getText().toUpperCase());
        this.nameTextField = nameTextField;
        this.nameTextField.setEditable(false);
        this.nameTextField.setBackground(null);
        this.nameTextField.setBorder(null);
        this.nameTextField.setForeground(Color.WHITE);
        
        this.add(this.nameLabel);
        this.add(this.nameTextField);
    }
    
    private void inicializarEntorno(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(128, 168, 255));
        this.setVisible(true);
    }
}
