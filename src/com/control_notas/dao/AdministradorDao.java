/**
 * @Interface
 * This class will be abstract the datails of our
 * class holding concrete implementatation of the persistence
 * logic
 */
package com.control_notas.dao;

import com.control_notas.model.Administrador;
import com.control_notas.model.Docente;
import com.control_notas.model.Estudiante;
import java.util.List;

/**
 *
 * @author Alejandro Jaimes
 */
public interface AdministradorDao {
    //Declaracion de los métodos para acceder a la base de datos.
    // Methods
    
    
    //CRUD ADMINISTRADOR
    
    void insertarAdministrador(Administrador _admin) throws Exception;
    
    void actualizarAdministrador(Administrador _admin) throws Exception;

    void borrarAdministrador(Administrador _admin) throws Exception;
    
    
    //CRUD DOCENTE
    void insertarDocente (Docente _doc) throws Exception;
    

    //CRUD ESTUDIANTE
    
    void insertarEstudiante(Estudiante _est) throws Exception;
    
    void actualizarEstudiante(Estudiante _est) throws Exception;

    void borrarEstudiante(Estudiante _est) throws Exception;
    
    
    
   
    
    
    

    //SEARCH
    List<Administrador> listarAdministradores() throws Exception;

    Administrador obtenerAdmin(int idPersona) throws Exception;
}
