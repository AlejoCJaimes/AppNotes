/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.gui;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Alejandro Jaimes
 */
public class jFramePrincipalEstudiante extends JFrame{
    
    public jFramePrincipalEstudiante(int idUsuario) 
    {
        inicializarEntorno();
    }
    private void inicializarEntorno() {
        String url = obtenerRutaIcono();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Estudiante (Demo Personalizado)");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }
     private String obtenerRutaIcono () {
        String ruta = ".\\src\\com\\control_notas\\gui\\images\\icon_application.png";
        return ruta;
    }
}
