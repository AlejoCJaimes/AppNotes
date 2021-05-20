/**
 * @Implementation
 *
 * This class will be a logic lawyer. Here,we make
 * sure that we have overriden the equals() method in our
 * Employee class correctly
 */
package com.control_notas.daoimpl;

import com.control_notas.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.control_notas.dao.UsuarioDao;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Jaimes
 */
public class UsuarioDaoImpl implements UsuarioDao {

    //private Conexion conn = new Conexion("jdbc:oracle:thin:@localhost:1521:XE",credentials.getLoginCredentialsDB(),credentials.getLoginCredentialsDB());
    //public Connection cn = null;
    private final List<Usuario> usuarios;

    public UsuarioDaoImpl() {
        usuarios = new ArrayList();
    }

    @Override
    public void insertarUsuario(Usuario _user) throws Exception {

        //| Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        try {

            PreparedStatement pstmt = conn.connect().prepareStatement("INSERT INTO USUARIOS (correo,clave,fechaCreacion,ID_rol,estatus) VALUES (?,?,CURRENT_TIMESTAMP,?,?)");
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

            PreparedStatement pstmt = conn.connect().prepareStatement("UPDATE USUARIOS SET clave = ? WHERE ID_usuario = ?");
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
            int _id = _user.getIdUsuario();
            PreparedStatement pstmt = conn.connect().prepareStatement("DELETE FROM USUARIOS WHERE ID_rol = ?");
            pstmt.setInt(1, _id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception {

        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        List<Usuario> lista = null;
        String query = "SELECT ID_USUARIO,CORREO,";
        query += "CLAVE,TO_CHAR(fechacreacion,'DD-MON-YYYY HH:MI AM') AS FECHA_CREACION, ID_ROL,";
        query += "ESTATUS ";
        query += "FROM USUARIOS WHERE ID_usuario = ?";
        try {

            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();

            lista = usuarios;
            while (rset.next()) {
                Usuario _user = new Usuario();
                _user.setIdUsuario(rset.getInt("ID_USUARIO"));
                _user.setCorreo(rset.getString("CORREO"));
                _user.setFechaCreacion(rset.getString("FECHA_CREACION"));
                _user.setClave(rset.getString("CLAVE"));
                _user.setIdRol(rset.getInt("ID_ROL"));
                _user.setStatus(rset.getInt("ESTATUS"));
                lista.add(_user);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return lista;
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) throws Exception {
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        Usuario _user = new Usuario();

        String query = "SELECT ID_USUARIO,CORREO,";
        query += "CLAVE,TO_CHAR(fechacreacion,'DD-MON-YYYY HH:MI AM') AS FECHA_CREACION, ID_ROL,";
        query += "ESTATUS ";
        query += "FROM USUARIOS WHERE ID_usuario = ?";
        try {

            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setInt(1, idUsuario);

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {

                _user.setIdUsuario(rset.getInt("ID_USUARIO"));
                _user.setCorreo(rset.getString("CORREO"));
                _user.setFechaCreacion(rset.getString("FECHA_CREACION"));
                _user.setClave(rset.getString("CLAVE"));
                _user.setIdRol(rset.getInt("ID_ROL"));

            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return _user;
    }

    @Override
    public int actionLogin(String correoUsuario, String passUsuario) throws Exception {
        
        
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        int idRolUsuario = 0;
        
        String query = "SELECT ID_ROL";
        query += " FROM USUARIOS WHERE CORREO = ? AND CLAVE = ?";
        try {

            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setString(1, correoUsuario);
            pstmt.setString(2, passUsuario);
            
            ResultSet rset = pstmt.executeQuery();
            
            while (rset.next()) {
                idRolUsuario = rset.getInt("ID_ROL");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return idRolUsuario;
    }

    @Override
    public int obtenerIdPersona(String correoUsuario) throws Exception {
         
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());

        int idUsuario = 0;
        
        String query = "SELECT ID_USUARIO";
        query += " FROM USUARIOS WHERE CORREO = ?";
        try {

            PreparedStatement pstmt = conn.connect().prepareStatement(query);
            pstmt.setString(1, correoUsuario);
            
            
            ResultSet rset = pstmt.executeQuery();
            
            while (rset.next()) {
                idUsuario = rset.getInt("ID_USUARIO");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return idUsuario;
    }

}
