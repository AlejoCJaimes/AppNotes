/**
 * @DAO Lawyer
 * 
 * Now, let’s try to perform some operations on our data store using 
 * the created persistence layer:
 */
package com.control_notas.gui;

import com.control_notas.dao.AdministradorDao;
import com.control_notas.dao.UsuarioDao;
import com.control_notas.daoimpl.AdministradorDaoImpl;
import com.control_notas.daoimpl.UsuarioDaoImpl;
import com.control_notas.model.Administrador;
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
        
//        //(correo,clave,fechaCreacion,ID_rol,estatus)
        Usuario user1 = new Usuario(); 
        user1.setIdUsuario(4);
        
         //ADMIN NUM_IDENTIFICACION,NOMBRE,APELLIDO,CARGO,ID_USUARIO
//         AdministradorDao adminDao = new AdministradorDaoImpl();
//         Administrador admin = new Administrador();
//         admin.setNum_identificacion("1005062032");
//         admin.setNombre("Administrador");
//         admin.setApellido("TEST");
//         admin.setCargo("Supervisor General");
//         admin.setIdUsuario(1);
         
         
        
        
        try
        {
           usuarioDao.borrarUsuario(user1);
//            System.out.println(user1.getCorreo());
//            System.out.println(user1.getFechaCreacion());
////         List<Usuario> _lista = usuarioDao.listarUsuarios();
////         
////         _lista.stream().map((model) -> {
////             System.out.println("ID: " + model.getIdUsuario());
////                return model;
////            }).map((model) -> {
////                System.out.println("Email: " + model.getCorreo());
////                return model;
////            }).map((model) -> {
////                System.out.println("Clave: " + model.getClave());
////                return model;
////            }).forEachOrdered((model) -> {
////                System.out.println("ID_rol: " + model.getIdRol());
////            });
//            System.out.println("Usuario traido Correctamente");
            //adminDao.insertarAdministrador(admin);
            System.out.println("Admin ingresado correctamente");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }  
    }
}
