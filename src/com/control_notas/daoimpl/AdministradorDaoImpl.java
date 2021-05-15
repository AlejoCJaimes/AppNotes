
package com.control_notas.daoimpl;

import com.control_notas.model.Administrador;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.control_notas.dao.AdministradorDao;
import com.control_notas.dao.UsuarioDao;
import com.control_notas.model.Usuario;
import java.sql.ResultSet;

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
        
       DataHostAccess credentials = new DataHostAccess();
       Conexion conn = new Conexion(credentials.getURLCrediantialsDB(),credentials.getLoginCredentialsDB(),credentials.getLoginCredentialsDB());
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
       int rolUsuario = 1;
       String query = "SELECT ID_PERSONA AS ID_ADMINISTRADOR, NUM_IDENTIFICACION AS IDENTIFICACION, NOMBRE, ";
       query += "APELLIDO, CARGO, PERSONAS.ID_USUARIO";
       query += "FROM PERSONAS ";
       query += "INNER JOIN USUARIOS ON PERSONAS.ID_USUARIO = USUARIOS.ID_USUARIO";
       query += "WHERE USUARIOS.ID_ROL = "+rolUsuario;
       
       //Creaciobn de lista
        List<Administrador> _lista = null;
       try 
       {
        PreparedStatement pstmt = conn.connect().prepareStatement(query);
        ResultSet rset = pstmt.executeQuery();
        
        //Traer datos
        Administrador _admin = new Administrador();
        _lista = administradores;
        
        while(rset.next())
        {
            _admin.setID_persona(rset.getInt("ID_ADMINISTRADOR"));
            _admin.setNum_identificacion(rset.getString("IDENTIFICACION"));
            _admin.setNombre(rset.getString("NOMBRE"));
            _admin.setApellido(rset.getString("APELLIDO"));
            _admin.setCargo(rset.getString("CARGO"));
            _admin.setIdUsuario(rset.getInt("ID_USUARIO"));
            _lista.add(_admin);
        }
       
        
       }catch(SQLException e)
       {
           throw e;
       } finally
       {
           conn.disconnect();
       }
           
        return _lista;
    }

    @Override
    public Administrador obtenerAdmin(int idPersona) throws Exception {
        
        DataHostAccess credentials = new DataHostAccess();
        Conexion conn = new Conexion (credentials.getURLCrediantialsDB(),credentials.getLoginCredentialsDB(),credentials.getLoginCredentialsDB());
    
        //query
       String query = "SELECT ID_PERSONA AS ID_ADMINISTRADOR, NUM_IDENTIFICACION AS IDENTIFICACION, NOMBRE, ";
       query += "APELLIDO, CARGO, ID_USUARIO";
       query += "FROM PERSONAS WHERE ID_PERSONA = ?";
       
       //Objeto
       Administrador _admin = new Administrador();
       try {
           PreparedStatement pstmt = conn.connect().prepareStatement(query);
           pstmt.setInt(1, idPersona);
           ResultSet rset = pstmt.executeQuery();
           
            while(rset.next())
        {
            _admin.setID_persona(rset.getInt("ID_ADMINISTRADOR"));
            _admin.setNum_identificacion(rset.getString("IDENTIFICACION"));
            _admin.setNombre(rset.getString("NOMBRE"));
            _admin.setApellido(rset.getString("APELLIDO"));
            _admin.setCargo(rset.getString("CARGO"));
            _admin.setIdUsuario(rset.getInt("ID_USUARIO"));
            
        }
       }catch(SQLException e)
       {
           throw e;
       }finally {
           conn.disconnect();
       }
       return _admin;
    }
    
    
}
