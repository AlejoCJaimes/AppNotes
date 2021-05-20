/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.daoimpl;

import com.control_notas.dao.Filter;


/**
 *
 * @author Alejandro Jaimes
 */
public class AuthenticationFilter implements Filter {

    private final String output = "Authentication Request:";

   
    @Override
    public int actionLogin(String correoUsuario, String passUsuario) throws Exception {
        System.out.println(output + correoUsuario + "...");
        return 0;
    }
    
}
