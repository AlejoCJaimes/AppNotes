/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.gui;

import com.control_notas.dao.UsuarioDao;
import com.control_notas.daoimpl.UsuarioDaoImpl;
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
        user1.setCorreo("robert@gmail.com");
        user1.setClave("5648");
        user1.setIdRol(3);
        user1.setStatus(1);
        
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
