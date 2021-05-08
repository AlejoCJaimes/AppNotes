/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.daoimpl;

import com.control_notas.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.control_notas.dao.UsuarioDao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Jaimes
 */
public class UsuarioDaoImpl implements UsuarioDao {

    //private Conexion conn = new Conexion("jdbc:oracle:thin:@localhost:1521:XE",credentials.getLoginCredentialsDB(),credentials.getLoginCredentialsDB());
    //public Connection cn = null;
    private final List<String> usuarios;

    public UsuarioDaoImpl() {
        usuarios = new ArrayList<>();
    }

    @Override
    public void insertarUsuario(Usuario _user) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        try {
            System.out.println(_user.getCorreo());
            //conn.connect();
            PreparedStatement pstmt = conn.connect().prepareStatement("INSERT INTO USUARIO (correo,clave,fechaCreacion,ID_rol,estatus) VALUES (?,?,CURRENT_TIMESTAMP,?,?)");
            pstmt.setString(1, _user.getCorreo());
            pstmt.setString(2, _user.getClave());
            pstmt.setInt(3, _user.getIdRol());
            pstmt.setInt(4, _user.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void actualizarUsuario(Usuario _user) throws Exception {

        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        try {

            PreparedStatement pstmt = conn.connect().prepareStatement("UPDATE USUARIO SET clave = ? WHERE ID_rol = ?");
            pstmt.setString(1, _user.getClave());
            pstmt.setInt(2, _user.getIdUsuario());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void borrarUsuario(Usuario _user) throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        try {

            PreparedStatement pstmt = conn.connect().prepareStatement("DELETE FROM USUARIO WHERE ID_rol = ?");
            pstmt.setInt(1, _user.getIdUsuario());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public List<String> listarUsuarios() throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        try {
            Statement stmt = conn.connect().createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM usuario");

            while (rset.next()) {
                System.out.println(rset.getString(1));

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return usuarios;
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
