package com.control_notas.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ActionListenerControlClics implements ActionListener{
    
    private final jPanelContentInformationCourse panelCursos;
    
    public ActionListenerControlClics(jPanelContentInformationCourse panelCursos){
        this.panelCursos = panelCursos;
    }
    
    public void escucharEventos() {
        panelCursos.buttonAsignar.forEach((boton) -> {
            boton.addActionListener(this);
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //jFrameTableStudents frameStudents;
        for (int i = 0; i < panelCursos.buttonAsignar.size(); i++) {
            if (e.getSource().equals(panelCursos.buttonAsignar.get(i))) {
//                frameStudents = new jFrameTableStudents(ArrayList<Integer> codigos, ArrayList<String> nombres, ArrayList<String> apellidos,
//                                ArrayList<String> correos, ArrayList<Double> corte1, ArrayList<Double> corte2, ArrayList<Double> corte3,i+1);
//                frameStudents.setVisible(true);
                JOptionPane.showMessageDialog(panelCursos, "En desarrollo...");
                return;
            }
        }
    }
    
}
