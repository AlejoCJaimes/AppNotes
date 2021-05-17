
package com.control_notas.gui;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Alejandro Jaimes
 */
public class jFramePrincipalAdministrador extends JFrame{
    
    public jFramePrincipalAdministrador() {
        
        inicializarEntorno();
    }
    private void inicializarEntorno() {
        String url = obtenerRutaIcono();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Docente (Demo Personalizado)");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
     private String obtenerRutaIcono () {
        String ruta = ".\\src\\com\\control_notas\\gui\\images\\icon_application.png";
        return ruta;
    }
}
