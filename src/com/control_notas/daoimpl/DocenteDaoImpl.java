/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.daoimpl;

import com.control_notas.dao.DocenteDao;
import com.control_notas.model.Carrera;
import com.control_notas.model.Clases;
import com.control_notas.model.Docente;
import com.control_notas.model.Materia;
import com.control_notas.model.Notas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Jaimes
 */
public class DocenteDaoImpl implements DocenteDao{

    private final List<Clases> datos_dashboard;
    private final List<Notas> estudiantes_cursos;
    
    
    public DocenteDaoImpl ()
    {
        datos_dashboard = new ArrayList();
        estudiantes_cursos = new ArrayList();
        
    }
    
     @Override
    public void asignarNota(Notas _nota) throws Exception {
          //| Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        try {

            PreparedStatement pstmt = conn.connect().prepareStatement("INSERT INTO NOTAS (CORTE,CALIFICACION,ID_ESTUDIANTE,ID_DOCENTE,ID_CLASES) VALUES (?,?,?,?,?)");
            pstmt.setFloat(1, _nota.getCorte());
            pstmt.setFloat(2, _nota.getCalificacion()); 
            pstmt.setInt(3, _nota.getID_persona()); //id_estudiante
            pstmt.setInt(4, _nota.getIdUsuario()); //aux id_docente
            pstmt.setInt(5, _nota.getID_clases());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }
    @Override
    public void actualizarNota(Notas _nota) throws Exception {
        //|Templates
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(),credentials.getLoginCredentialsDB(),credentials.getLoginCredentialsDB());
        
        try 
        {
          //Statement
          PreparedStatement pstmt = conn.connect().prepareStatement("UPDATE NOTAS SET calificacion = ? WHERE ID_nota = ? AND ID_estudiante = ? AND ID_clases = ?");
          pstmt.setFloat(1, _nota.getCalificacion());
          pstmt.setInt(2, _nota.getID_nota());
          pstmt.setInt(3, _nota.getID_persona());
          pstmt.setInt(4, _nota.getID_clases());
          pstmt.executeUpdate();
          
        }catch (SQLException e)
        {
            throw e;
        }finally {
            conn.disconnect();
        }
        
    }
    
    
    
    @Override
    public List<Docente> listarDocentes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Clases> listarDashboarDocente(int idDocente) throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

    
        String query = "SELECT p.nombre DOCENTE, m.nombre MATERIA, ces.id_clases ID_CLASES, COUNT(*) ESTUDIANTES";
        query +=" FROM MATERIAS m ";
        query +=" INNER JOIN CLASES c ON c.id_materia = m.id_materia";
        query +=" INNER JOIN CLASES_ESTUDIANTES ces ON ces.id_clases = c.id_clases";
        query +=" INNER JOIN PERSONAS p ON p.id_persona = c.id_docente";
        query +=" INNER JOIN USUARIOS u ON u.id_usuario = p.id_usuario ";
        query +=" INNER JOIN ROLES r ON r.id_rol = u.id_rol";
        query +=" GROUP BY p.nombre,m.nombre,ces.id_clases, r.id_rol, p.id_persona";
        query +=" HAVING p.id_persona = ?";
        
        //Creaciobn de lista
        List<Clases> _lista = null;
        try {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setInt(1, idDocente);
            ResultSet rset = pstmt.executeQuery();

            //Traer datos
            
            _lista = datos_dashboard;
            
            
            while (rset.next()) {
                Clases _clases = new Clases();
                Materia _materia = new Materia();
                System.out.println(rset.getString("DOCENTE"));
                System.out.println(rset.getString("MATERIA"));
                System.out.println(rset.getInt("ID_CLASES"));
                System.out.println(rset.getInt("ESTUDIANTES"));
                
                _clases.setNombre(rset.getString("DOCENTE"));
                _materia.setNombre(rset.getString("MATERIA"));
                _clases.setMateria(_materia);
                _clases.setID_clases(rset.getInt("ID_CLASES"));
                _clases.setNumero_estudiantes(rset.getInt("ESTUDIANTES"));
               _lista.add(_clases);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return _lista;
    }

    @Override
    public Docente obtenerDocente(int idPersona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notas> listarEstudiantesCurso(int idEstudiante, int idClases) throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

    
        String query = " SELECT p.id_persona as ID_ESTUDIANTE,p.nombre AS NOMBRE, p.apellido AS APELLIDO, ";
        query += "p.num_identificacion AS IDENTIFICACION, m.nombre AS MATERIAS, u.correo AS CORREO";
        query += ", n.corte as CORTE, car.nombre as CARRERA ,"; 
        query += "n.calificacion as NOTA";
        query +="FROM NOTAS n";
        query +="INNER JOIN PERSONAS p ON p.id_persona = n.id_estudiante";
        query +="INNER JOIN USUARIOS u ON p.id_usuario = u.id_usuario";
        query +="INNER JOIN CLASES c ON c.id_clases = n.id_clases";
        query +="INNER JOIN CARRERAS car ON car.cod_carrera = p.cod_carrera";
        query +="INNER JOIN MATERIAS m ON c.id_materia = m.id_materia";
        query +="WHERE p.id_persona = ? AND c.id_clases = ? ";
        
        
        //Creaciobn de lista
        List<Notas> _lista = null;
        try {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setInt(1, idEstudiante);
            pstmt.setInt(2, idClases);
            ResultSet rset = pstmt.executeQuery();

            //Traer datos
            _lista = estudiantes_cursos;
            
            
            while (rset.next()) {
                Notas _notas = new Notas();
                Materia _materia = new Materia();
                Carrera _carrera = new Carrera();
                _notas.setID_persona(rset.getInt("ID_DOCENTE"));
                _notas.setNombre(rset.getString("NOMBRE"));
                _notas.setApellido(rset.getString("APELLIDO"));
                _notas.setNum_identificacion(rset.getString("IDENTIFICACION"));
                _materia.setNombre(rset.getString("MATERIAS"));
                _notas.setMateria(_materia);
                _notas.setCorreo(rset.getString("CORREO"));
                _notas.setCorte(rset.getFloat("CORTE"));
                _carrera.setNombre(rset.getString("CARRERA"));
                _notas.setCarrera(_carrera);
                _notas.setCalificacion(rset.getFloat("NOTA"));
                _lista.add(_notas);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return _lista;
        
    }

    @Override
    public List<Notas> listarEstudiantesCurso(int idDocente) throws Exception {
       DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

    
        String query = " SELECT p.id_persona as ID_ESTUDIANTE, p.nombre AS NOMBRE, p.apellido AS APELLIDO, ";
        query += " p.num_identificacion AS IDENTIFICACION, m.nombre AS MATERIAS, u.correo AS CORREO, ";
        query += " n.corte as CORTE, car.nombre as CARRERA ,"; 
        query += " n.calificacion as NOTA";
        query +=" FROM NOTAS n";
        query +=" INNER JOIN PERSONAS p ON p.id_persona = n.id_estudiante";
        query +=" INNER JOIN USUARIOS u ON p.id_usuario = u.id_usuario";
        query +=" INNER JOIN CLASES c ON c.id_clases = n.id_clases";
        query +=" INNER JOIN CARRERAS car ON car.cod_carrera = p.cod_carrera";
        query +=" INNER JOIN MATERIAS m ON c.id_materia = m.id_materia";
        query +=" WHERE c.id_docente = ?";
        
        
        //Creaciobn de lista
        List<Notas> _lista = null;
        try {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setInt(1, idDocente);
            ResultSet rset = pstmt.executeQuery();

            //Traer datos
            _lista = estudiantes_cursos;
            
            
            while (rset.next()) {
                Notas _notas = new Notas();
                Materia _materia = new Materia();
                Carrera _carrera = new Carrera();
                _notas.setID_persona(rset.getInt("ID_ESTUDIANTE"));
                _notas.setNombre(rset.getString("NOMBRE"));
                _notas.setApellido(rset.getString("APELLIDO"));
                _notas.setNum_identificacion(rset.getString("IDENTIFICACION"));
                _materia.setNombre(rset.getString("MATERIAS"));
                _notas.setMateria(_materia);
                _notas.setCorreo(rset.getString("CORREO"));
                _notas.setCorte(rset.getFloat("CORTE"));
                _carrera.setNombre(rset.getString("CARRERA"));
                _notas.setCarrera(_carrera);
                _notas.setCalificacion(rset.getFloat("NOTA"));
                _lista.add(_notas);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return _lista; 
    }

    

   
    
}
