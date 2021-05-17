package com.control_notas.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class jPanelContentInformationCourse extends JPanel{
    
    private final JScrollPane panelDeslizable = new JScrollPane();
    private jPanelSubContentInformationCourse panelCurso;
    private ActionListenerControlClics controlClics;
    public ArrayList<JButton> buttonAsignar = new ArrayList<>();
    
    public jPanelContentInformationCourse(ArrayList<String> materias, ArrayList<Integer> nEstudiantes, ArrayList<Integer> ubiSemestral){
        Box box = new Box(BoxLayout.Y_AXIS);
        JButton bAux;
        
        for(int i= 0; i< materias.size(); i++){
            panelCurso = 
                    new jPanelSubContentInformationCourse(materias.get(i), nEstudiantes.get(i), ubiSemestral.get(i), (i+1));
            
            bAux = this.getButtonAsignar(i+1);
            buttonAsignar.add(bAux);
            panelCurso.add(bAux, BorderLayout.CENTER);
            box.add(panelCurso);
        }
        panelDeslizable.setViewportView(box);
        this.add(panelDeslizable, BorderLayout.CENTER);
        this.inicializarEntorno();
        this.ejecutar();
    }
    
    private void ejecutar() {
        ActionListenerControlClics ejecutarAcciones = new ActionListenerControlClics(this);
        ejecutarAcciones.escucharEventos();
    }
    
    private JButton getButtonAsignar(int index){
        JButton b = new JButton();
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setBackground(Color.WHITE);
        b.setText("ASIGNAR NOTAS [CURSO "+index+"]");
        b.setToolTipText("Asignar Notas Curso No. "+index);
        return (b);
    }
     private void inicializarEntorno(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Cursos Docente"));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }
}
