/**
 * @Interface
 * This class will be abstract the datails of our
 * class holding concrete implementatation of the persistence
 * logic
 */
package com.control_notas.dao;

import com.control_notas.model.Usuario;
import java.util.List;

/**
 *
 * @author Alejandro Jaimes
 */
public interface UsuarioDao {

    //Declaracion de los métodos para acceder a la base de datos.
    // Methods+
    void insertarUsuario(Usuario _user) throws Exception;

    void actualizarUsuario(Usuario _user) throws Exception;

    void borrarUsuario(Usuario _user) throws Exception;

    //SEARCH
    List<Usuario> listarUsuarios() throws Exception;

    Usuario obtenerUsuario(int idUsuario) throws Exception;

}
