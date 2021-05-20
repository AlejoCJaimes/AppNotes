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

    
        String query = " SELECT m.nombre MATERIA, c.id_clases ID_CURSO, ces.id_clases ID_CLASE, c.semestre SEMESTRE, COUNT(*) ESTUDIANTES ";
        query += " FROM MATERIAS m "; 
        query += " INNER JOIN CLASES c ON c.id_materia = m.id_materia ";
        query += " INNER JOIN CLASES_ESTUDIANTES ces ON ces.id_clases = c.id_clases ";
        query += " GROUP BY m.nombre, c.semestre, c.id_clases, c.id_docente, ces.id_clases ";
        query += " HAVING c.id_docente = ? "; 
        query += " UNION ";
        query += " SELECT  m.nombre MATERIA, c.id_clases ID_CURSO, (SELECT 0 FROM DUAL)ID_CLASES, c.semestre SEMESTRE, (SELECT 0 FROM DUAL) ESTUDIANTES ";
        query += " FROM MATERIAS m "; 
        query += " INNER JOIN CLASES c ON c.id_materia = m.id_materia ";
        query += " GROUP BY m.nombre, c.semestre, c.id_clases, c.id_docente ";
        query += " HAVING c.id_docente = ? AND c.id_clases NOT IN (SELECT ces.id_clases FROM CLASES_ESTUDIANTES ces) ";
        
        //Creaciobn de lista
        List<Clases> _lista = null;
        try {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setInt(1, idDocente);
            pstmt.setInt(2, idDocente);
            ResultSet rset = pstmt.executeQuery();

            //Traer datos
            
            _lista = datos_dashboard;
            
            
            while (rset.next()) {
                Clases _clases = new Clases();
                Materia _materia = new Materia();
                
                _materia.setNombre(rset.getString("MATERIA"));
                _clases.setMateria(_materia);
                _clases.setID_clases(rset.getInt("ID_CLASE"));
                _clases.setID_curso(rset.getInt("ID_CURSO"));
                _clases.setSemestre_clase(rset.getInt("SEMESTRE"));
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
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        
       //query
        int rolUsuario = 2;
        String query = " SELECT p.ID_PERSONA AS ID_DOCENTE, p.NUM_IDENTIFICACION AS IDENTIFICACION, p.NOMBRE as NOMBRE, ";
        query += " p.APELLIDO AS APELLIDO, p.TITULO_DOC AS TITULO, u.ID_USUARIO AS ID_USER, u.CORREO AS CORREO ";
        query += " FROM PERSONAS p ";
        query += " INNER JOIN USUARIOS u ON p.ID_USUARIO = u.ID_USUARIO ";
        query += " WHERE u.ID_ROL = ? AND p.ID_PERSONA = ? ";
        
        //Instancia docente
        Docente _doc = new Docente();
        
        try {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setInt(1, rolUsuario);
            pstmt.setInt(2, idPersona);
            ResultSet rset = pstmt.executeQuery();
            
            while(rset.next())
            {
                _doc.setID_persona(rset.getInt("ID_DOCENTE"));
                _doc.setNum_identificacion(rset.getString("IDENTIFICACION"));
                _doc.setNombre(rset.getString("NOMBRE"));
                _doc.setApellido(rset.getString("APELLIDO"));
                _doc.setTituloDocente(rset.getString("TITULO"));
                _doc.setIdUsuario(rset.getInt("ID_USER"));
                _doc.setCorreo(rset.getString("CORREO"));
            }
        }catch(SQLException e)
        {
            throw e;
        } finally {
            conn.disconnect();
        }
       return _doc;
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

    @Override
    public int cursosDocente(int IdDocente) throws Exception {
       
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(),credentials.getLoginCredentialsDB());
       
        String query = " SELECT COUNT(*) ";
              query += " FROM CLASES ";
              query += " WHERE id_docente = ? ";
        
        int num_cursos = 0;
        try
        {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setInt(1, IdDocente);
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                num_cursos = rset.getInt(1);
            }

         
       
        }catch(SQLException e)
        {
            throw e;
        }finally
        {
            conn.disconnect();
        }
              
        return num_cursos;
    }

//    @Override
//    public List<Clases> listarCursosDocente(int idDocente) throws Exception {
//        
//        DataHostAccess credentials = new DataHostAccess();
//        Conexion conn = new Conexion (credentials.getURLCrediantialsDB(),credentials.getLoginCredentialsDB(),credentials.getLoginCredentialsDB());
//        
//        String query = " SELECT c.id_clases as ID_CLASE, m.nombre as MATERIA, c.semestre as SEMESTRE ";
//        query += " FROM MATERIAS m ";
//        query += " INNER JOIN CLASES c ON c.id_materia = m.id_materia ";
//        query += " WHERE c.id_docente = ?";
//        
//        
//        
//        List<Clases> _lista = null;
//        
//        try
//        {
//            PreparedStatement pstmt = conn.connect().prepareStatement(query);
//            pstmt.setInt(1, idDocente);
//            ResultSet rset = pstmt.executeQuery();
//            _lista = datos_dashboard;
//            
//            while(rset.next())
//            {
//                
//                Clases _clases = new Clases();
//                Materia _materia = new Materia();
//                _clases.setID_clases(rset.getInt("ID_CLASE"));
//                _materia.setNombre(rset.getString("MATERIA"));
//                _clases.setMateria(_materia);
//                _clases.setSemestre_clase(rset.getInt("SEMESTRE"));
//                _lista.add(_clases);
//            }
//            
//        }catch(SQLException e)
//        {
//            throw e;
//        } finally {
//            conn.disconnect();
//        }
//        return _lista;
//    }
//    
    

    

   
    
}
