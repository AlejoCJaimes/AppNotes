package com.control_notas.gui;

import com.control_notas.model.Clases;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class jPanelSubContentInformationCourse extends JPanel {

    //Constructor
    public jPanelSubContentInformationCourse(Clases clase) {
        
        this.add(new jPanelRefInformation(new JLabel("Nombre Materia: "), new JTextField(clase.getMateria().getNombre())));
        this.add(new jPanelRefInformation(new JLabel("Estudiantes Inscritos: "), new JTextField(String.valueOf(clase.getNumero_estudiantes()))));
        this.add(new jPanelRefInformation(new JLabel("Ubicación Semestral: "), new JTextField(String.valueOf(clase.getSemestre_clase()))));
        this.inicializarEntorno(clase.getID_curso());
    }
    
    private void inicializarEntorno(int idCurso){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(new Color(18, 17, 95));        
        this.setBorder(BorderFactory.createTitledBorder(this.getBorder(), "ID Curso. "+idCurso, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION
                                                            , this.getFont(), Color.WHITE));
        this.setVisible(true);
    }
}
