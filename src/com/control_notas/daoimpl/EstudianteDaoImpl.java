/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.daoimpl;

import com.control_notas.dao.EstudianteDAO;
import com.control_notas.dao.UsuarioDao;

import com.control_notas.model.Estudiante;
import com.control_notas.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Jaimes
 */
public class EstudianteDaoImpl implements EstudianteDAO {

    private final List<Estudiante> estudiantes;

    public EstudianteDaoImpl() {
        estudiantes = new ArrayList();
    }

    @Override
    public void insertarEstudiante(Estudiante _est) throws Exception {
        // Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        try {

            PreparedStatement pstmt = conn.connect().prepareStatement("INSERT INTO PERSONAS (NUM_IDENTIFICACION,NOMBRE,APELLIDO,NUM_SEMESTRE,COD_CARRERA,ID_USUARIO) VALUES (?,?,?,?,?,?)");
            pstmt.setString(1, _est.getNum_identificacion());
            pstmt.setString(2, _est.getNombre());
            pstmt.setString(3, _est.getApellido());
            pstmt.setInt(4, _est.getNum_semestre());
            pstmt.setString(5, _est.getCod_carrera());
            pstmt.setInt(6, _est.getIdUsuario());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void actualizarEstudiante(Estudiante _est) throws Exception {
        // Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        String query = "UPDATE PERSONAS";
        query += "NOMBRE = ?,APELLIDO = ?,NUM_SEMESTRE = ?,COD_CARRERA = ?";
        query += "WHERE ID_USUARIO = ?";
        try {

            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setString(1, _est.getNombre());
            pstmt.setString(2, _est.getApellido());
            pstmt.setInt(3, _est.getNum_semestre());
            pstmt.setString(4, _est.getCod_carrera());
            pstmt.setInt(5, _est.getIdUsuario());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void borrarEstudiante(Estudiante _est) throws Exception {
        // Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        try {
            int _id = _est.getIdUsuario();

            //Eliminar Persona
            PreparedStatement pstmt = conn.connect().prepareStatement("DELETE FROM PERSONAS WHERE ID_USUARIO = ?");
            pstmt.setInt(1, _id);

            //Dao Usuarios
            UsuarioDao usuarioDao = new UsuarioDaoImpl();

            Usuario _user = usuarioDao.obtenerUsuario(_id);
            //Eliminar Usuario
            usuarioDao.borrarUsuario(_user);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public List<Estudiante> listarEstudiantes() throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
//                ID_PERSONA                                NOT NULL NUMBER(10)
//         NUM_IDENTIFICACION                        NOT NULL VARCHAR2(15)
//         NOMBRE                                    NOT NULL VARCHAR2(60)
//         APELLIDO                                  NOT NULL VARCHAR2(60)
//         CARGO                                              VARCHAR2(100)
//         COD_CARRERA                                        VARCHAR2(10)
//         ID_USUARIO                                NOT NULL NUMBER(10)
//         NUM_SEMESTRE                                       NUMBER(3)
//         TITULO_DOC
        //query
        int rolUsuario = 2;
        String query = "SELECT ID_PERSONA AS ID_ESTUDIANTE, NUM_IDENTIFICACION AS IDENTIFICACION, NOMBRE, ";
        query += "APELLIDO, NUM_SEMESTRE AS SEMESTRE,COD_CARRERA, ID_USUARIO";
        query += "FROM PERSONAS ";
        query += "INNER JOIN USUARIOS ON PERSONAS.ID_USUARIO = USUARIOS.ID_USUARIO";
        query += "WHERE USUARIOS.ID_ROL = " + rolUsuario;

        //Creaciobn de lista
        List<Estudiante> _lista = null;
        try {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();

            //Traer datos
            Estudiante _est = new Estudiante();
            _lista = estudiantes;

            while (rset.next()) {
                _est.setID_persona(rset.getInt("ID_ESTUDIANTE"));
                _est.setNum_identificacion(rset.getString("IDENTIFICACION"));
                _est.setNombre(rset.getString("NOMBRE"));
                _est.setApellido(rset.getString("APELLIDO"));
                _est.setNum_semestre(rset.getInt("SEMESTRE"));
                _est.setCod_carrera(rset.getString("COD_CARRERA"));
                _est.setIdUsuario(rset.getInt("ID_USUARIO"));
                _lista.add(_est);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return _lista;
    }

    @Override
    public Estudiante obtenerEstudiante(int idPersona) throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        //Traer datos
        Estudiante _est = new Estudiante();
        //query
        int rolUsuario = 2;
        String query = "SELECT ID_PERSONA AS ID_ESTUDIANTE, NUM_IDENTIFICACION AS IDENTIFICACION, NOMBRE, ";
        query += "APELLIDO, NUM_SEMESTRE AS SEMESTRE,COD_CARRERA, ID_USUARIO";
        query += "FROM PERSONAS ";
        query += "INNER JOIN USUARIOS ON PERSONAS.ID_USUARIO = USUARIOS.ID_USUARIO";
        query += "WHERE USUARIOS.ID_ROL = ? AND PERSONAS.ID_PERSONA = ?";

        try {
            PreparedStatement pstmt = conn.connect().prepareStatement(query);

            pstmt.setInt(1, rolUsuario);
            pstmt.setInt(2, idPersona);

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                _est.setID_persona(rset.getInt("ID_ESTUDIANTE"));
                _est.setNum_identificacion(rset.getString("IDENTIFICACION"));
                _est.setNombre(rset.getString("NOMBRE"));
                _est.setApellido(rset.getString("APELLIDO"));
                _est.setNum_semestre(rset.getInt("SEMESTRE"));
                _est.setCod_carrera(rset.getString("COD_CARRERA"));
                _est.setIdUsuario(rset.getInt("ID_USUARIO"));
                ;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return _est;
    }

}
