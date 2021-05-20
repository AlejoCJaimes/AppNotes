
package com.control_notas.gui;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Alejandro Jaimes
 */
public class jFramePrincipalAdministrador extends JFrame{
    
    public jFramePrincipalAdministrador(int idUsuario) {
        
        inicializarEntorno();
    }
    private void inicializarEntorno() {
        String url = obtenerRutaIcono();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        this.setTitle("Administrador (Demo Personalizado)");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }
     private String obtenerRutaIcono () {
        String ruta = ".\\src\\com\\control_notas\\gui\\images\\icon_application.png";
        return ruta;
    }
}
