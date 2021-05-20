/**
 * @DAO Lawyer
 *
 * Now, let’s try to perform some operations on our data store using
 * the created persistence layer:
 */
package com.control_notas.gui;

import com.control_notas.dao.DocenteDao;
import com.control_notas.daoimpl.AuthenticationFilter;
import com.control_notas.daoimpl.DocenteDaoImpl;
import com.control_notas.daoimpl.FilterManager;
import com.control_notas.daoimpl.Target;
import com.control_notas.model.Docente;
import com.control_notas.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Jaimes
 */
public class AccessNotas {

    

    public static void main(String[] args) throws Exception{
        
        //LOGIN JFRAME
        jFrameLogin frmLogin = new jFrameLogin();
        frmLogin.setVisible(true);
        
        
        //_user.sendCredentials(username, password)
        
        
        
        
        
//        DocenteDao daoDoc = new DocenteDaoImpl();
//
//        //Instancia Docente
//        Docente _doc;
//        
//        
//        try {
//         _doc = daoDoc.obtenerDocente(33);
//         System.out.println(_doc.getNombre());
//        }catch(SQLException e)
//        {
//            System.out.println(e);
//        }
        
//        info();
//        jFramePrincipalDocente pDoc = new jFramePrincipalDocente(materias, nEstudiantes, ubiSemestral);
//        pDoc.setVisible(true);
    }

  

}
