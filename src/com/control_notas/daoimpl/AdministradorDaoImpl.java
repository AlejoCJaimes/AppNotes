
package com.control_notas.daoimpl;

import com.control_notas.model.Administrador;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.control_notas.dao.AdministradorDao;
import com.control_notas.dao.UsuarioDao;
import com.control_notas.model.Usuario;

/**
 *
 * @author Alejandro Jaimes
 */
public class AdministradorDaoImpl implements AdministradorDao{
    
    private final List<Administrador> administradores;

    public AdministradorDaoImpl() {
        administradores = new ArrayList();
    }

    @Override
    public void insertarAdministrador(Administrador _admin) throws Exception {
         // Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        
        
        try {
            
            PreparedStatement pstmt = conn.connect().prepareStatement("INSERT INTO PERSONAS (NUM_IDENTIFICACION,NOMBRE,APELLIDO,CARGO,ID_USUARIO) VALUES (?,?,?,?,?)");
            pstmt.setString(1, _admin.getNum_identificacion());
            pstmt.setString(2, _admin.getNombre());
            pstmt.setString(3, _admin.getApellido());
            pstmt.setString(4, _admin.getCargo());
            pstmt.setInt(5, _admin.getIdUsuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void actualizarAdministrador(Administrador _admin) throws Exception {
       // Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        
        
        try {
            
            PreparedStatement pstmt = conn.connect().prepareStatement("UPDATE PERSONAS SET NUM_IDENTIFICACION = ? , NOMBRE = ?,APELLIDO = ? ,CARGO = ? WHERE ID_USUARIO = ?");
            pstmt.setString(1, _admin.getNum_identificacion());
            pstmt.setString(2, _admin.getNombre());
            pstmt.setString(3, _admin.getApellido());
            pstmt.setString(4, _admin.getCargo());
            pstmt.setInt(5, _admin.getIdUsuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void borrarAdministrador(Administrador _admin) throws Exception {
        // Templates.
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion(credentials.getURLCrediantialsDB(), credentials.getLoginCredentialsDB(), credentials.getLoginCredentialsDB());
        
        
        try {
            int _id = _admin.getIdUsuario();
            
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
    public List<Administrador> listarAdministradores() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Administrador obtenerAdmin(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
