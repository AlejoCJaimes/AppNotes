/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.daoimpl;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Alejandro Jaimes
 */
public class Conexion {
     /**
     * Atributos de la conexion
     */
    private final String jdbcURL;
    private final String user;
    private final String password;

    /**
     * Data Source
     */
    private OracleDataSource ods = null;
    protected Connection conn = null;

    /**
     * Mensaje error
     *
     */
    /**
     *
     * @param jdbcURL
     * @param user
     * @param password
     */
    public Conexion(String jdbcURL, String user, String password) {
        this.jdbcURL = jdbcURL;
        this.user = user;
        this.password = password;
    }

   

    public Connection connect() {
        try {
            
            if (conn == null) {
             
                ods = new OracleDataSource();
                ods.setURL(jdbcURL);
                ods.setUser(user);
                ods.setPassword(password);
                conn = ods.getConnection();
                System.out.println("LOG01");

            }

        } catch (SQLException errorMessage) {
            System.out.println(errorMessage.getMessage());
        }
        return conn;
    }

    public boolean disconnect() {
        try {
            if (conn != null) {
                if (!conn.isClosed()) {
                    conn.close(); //Close the connecction
                    ods.close(); //Close Oracle Data Source
                    System.out.println("LOG02");
                }
            }

        } catch (SQLException errorMessage) {
            System.out.println(errorMessage.getMessage());
        }
        return true;
    }
}
