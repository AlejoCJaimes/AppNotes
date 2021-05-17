package com.control_notas.gui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class jPanelSubContentInformationCourse extends JPanel {

    //Constructor
    public jPanelSubContentInformationCourse(String pNomMateria, int nEst, int uSem, int index) {
        
        this.add(new jPanelRefInformation(new JLabel("Nombre Materia: "), new JTextField(pNomMateria)));
        this.add(new jPanelRefInformation(new JLabel("Estudiantes Inscritos: "), new JTextField(String.valueOf(nEst))));
        this.add(new jPanelRefInformation(new JLabel("Ubicación Semestral: "), new JTextField(String.valueOf(uSem))));
        
        this.inicializarEntorno(index);
    }
    
    private void inicializarEntorno(int index){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(new Color(18, 17, 95));        
        this.setBorder(BorderFactory.createTitledBorder(this.getBorder(), "Curso No. "+index, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION
                                                            , this.getFont(), Color.WHITE));
        this.setVisible(true);
    }
}
