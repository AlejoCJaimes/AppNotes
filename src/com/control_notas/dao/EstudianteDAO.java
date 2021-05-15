/**
 * @Interface
 * This class will be abstract the datails of our
 * class holding concrete implementatation of the persistence
 * logic
 */
package com.control_notas.dao;


import com.control_notas.model.Estudiante;
import java.util.List;


public interface EstudianteDAO {
    //Declaracion de los métodos para acceder a la base de datos.
    // Methods
    void insertarEstudiante(Estudiante _est) throws Exception;
    
    void actualizarEstudiante(Estudiante _est) throws Exception;

    void borrarEstudiante(Estudiante _est) throws Exception;

    //SEARCH
    List<Estudiante> listarEstudiantes() throws Exception;

    Estudiante obtenerEstudiante(int idPersona) throws Exception;
}
