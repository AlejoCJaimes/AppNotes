/**
 * @Interface
 * This class will be abstract the datails of our
 * class holding concrete implementatation of the persistence
 * logic
 */
package com.control_notas.dao;

import com.control_notas.model.Administrador;
import java.util.List;

/**
 *
 * @author Alejandro Jaimes
 */
public interface AdministradorDao {
    //Declaracion de los métodos para acceder a la base de datos.
    // Methods
    void insertarAdministrador(Administrador _admin) throws Exception;
    
    void actualizarAdministrador(Administrador _admin) throws Exception;

    void borrarAdministrador(Administrador _admin) throws Exception;

    //SEARCH
    List<Administrador> listarAdministradores() throws Exception;

    Administrador obtenerAdmin(int idUsuario) throws Exception;
}
