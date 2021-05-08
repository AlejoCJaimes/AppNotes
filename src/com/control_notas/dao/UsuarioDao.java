/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.dao;

import com.control_notas.model.Usuario;
import java.util.List;

/**
 *
 * @author Alejandro Jaimes
 */
public interface UsuarioDao {

    //Declaracion de los métodos para acceder a la base de datos.
    // Methods
    void insertarUsuario(Usuario _user) throws Exception;

    void actualizarUsuario(Usuario _user) throws Exception;

    void borrarUsuario(Usuario _user) throws Exception;

    //SEARCH
    List<String> listarUsuarios() throws Exception;

    Usuario obtenerUsuario(int idUsuario) throws Exception;

}
