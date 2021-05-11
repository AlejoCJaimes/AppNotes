/**
 * @DAO Lawyer
 * 
 * Now, let’s try to perform some operations on our data store using 
 * the created persistence layer:
 */
package com.control_notas.gui;

import com.control_notas.dao.UsuarioDao;
import com.control_notas.daoimpl.UsuarioDaoImpl;
import com.control_notas.model.Administrador;
import com.control_notas.model.Carrera;
import com.control_notas.model.Materia;
import com.control_notas.model.Persona;
import com.control_notas.model.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Alejandro Jaimes
 */
public class AccessNotas {
     /**
     * 
     * @param args 
     * @throws java.lang.Exception 
     */
    public static void main (String [] args) throws  Exception {
       
        //Objeto para manipular el DAO
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        
        //(correo,clave,fechaCreacion,ID_rol,estatus)
        Usuario user1 = new Usuario(); 
        user1.setCorreo("dago@unipamplona.edu.co");
        user1.setClave("5848");
        user1.setIdRol(2);
        user1.setStatus(0);
        Carrera car = new Carrera();
        Administrador admin = new Administrador();
        
        try
        {
         usuarioDao.insertarUsuario(user1);
            System.out.println("Usuario Insertado Correctamente");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }  
    }
}
