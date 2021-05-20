
package com.control_notas.daoimpl;

import com.control_notas.dao.UsuarioDao;
import com.control_notas.gui.jFramePrincipalAdministrador;
import com.control_notas.gui.jFramePrincipalDocente;
import com.control_notas.gui.jFramePrincipalEstudiante;
import java.sql.SQLException;

public class Target {
    
    public int actionlogin(String correoUsuario, String passUsuario) throws Exception {
        
        int idRol = 0;
        int idUsuario = 0;
        //instance Usuario Dao
        UsuarioDao _user = new UsuarioDaoImpl();
        
        try
        {
          idRol = _user.actionLogin(correoUsuario, passUsuario);
          idUsuario = _user.obtenerIdPersona(correoUsuario);
        }catch(SQLException e)
        {
            System.out.println(e);
        }
        if (idRol == 1) {
            
            jFramePrincipalAdministrador frmAdministrador = new jFramePrincipalAdministrador(idUsuario);
            frmAdministrador.setVisible(true);
            
            
        }

        if (idRol == 2) {
            
            jFramePrincipalDocente frmDocente = new jFramePrincipalDocente(idUsuario);
            frmDocente.setVisible(true);
            
        }

        if (idRol == 3) {
            jFramePrincipalEstudiante frmEstudiante = new jFramePrincipalEstudiante(idUsuario);
            frmEstudiante.setVisible(true);
        }
        return idRol;
    }
}
