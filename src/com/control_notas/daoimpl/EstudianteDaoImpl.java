/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.daoimpl;

import com.control_notas.dao.EstudianteDAO;


import com.control_notas.model.Estudiante;
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
    public List<Estudiante> listarEstudiantes() throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
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
