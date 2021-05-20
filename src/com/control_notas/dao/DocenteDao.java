
package com.control_notas.dao;

import com.control_notas.model.Clases;
import com.control_notas.model.Docente;
import com.control_notas.model.Notas;
import java.util.List;

/**
 *
 * @author Alejandro Jaimes
 */
public interface DocenteDao {
    //Declaracion de los métodos para acceder a la base de datos.
    // Methods
    
    
    void asignarNota(Notas _nota) throws Exception;
    
    void actualizarNota (Notas _nota) throws Exception;
    
    
    
    //SEARCH
    List<Docente> listarDocentes() throws Exception;
    
    List<Notas> listarEstudiantesCurso(int idDocente) throws Exception;
    
    List<Notas> listarEstudiantesCurso(int idEstudiante, int idCLases) throws Exception;
    
    List<Clases> listarDashboarDocente(int idDocente) throws Exception; //Nombre, ID_clase
    
//    List<Clases> listarCursosDocente (int idDocente) throws Exception;
    
    Docente obtenerDocente(int idPersona) throws Exception;
    
    int cursosDocente (int IdDocente) throws Exception;
    
}
