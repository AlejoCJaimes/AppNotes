/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control_notas.daoimpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Alejandro Jaimes
 */
public class DataHostAccess {

    private String dirFile;
    
    public DataHostAccess() {};
     

    public String getLoginCredentialsDB() {

        //Define Variables
        String aux = "";
        String temp = "";
        String bfRead;

        //Route Access
        dirFile = "C:\\DAOAccess\\Released V.1\\crede$ntialAcce$$.txt";

        try {
            BufferedReader bf = new BufferedReader(new FileReader(dirFile));

            while ((bfRead = bf.readLine()) != null) {

                temp += bfRead;
            }
            aux = temp;

        } catch (IOException ex) {
            System.err.println("ERR01: DataHostText --- Access" + ex);
        }
        //DataAccess
        

        return  getToken(aux);

    }
    
    public String getURLCrediantialsDB() {
        
         //Define Variables
        String aux = "";
        String temp = "";
        String bfRead;

        //Route Access
        dirFile = "C:\\DAOAccess\\Released V.1\\crede$ntialAcce$$.txt";

        try {
            BufferedReader bf = new BufferedReader(new FileReader(dirFile));

            while ((bfRead = bf.readLine()) != null) {

                temp += bfRead;
            }
            aux = temp;

        } catch (IOException ex) {
            System.err.println("ERR01: DataHostText --- Access" + ex);
        }
        //DataAccess
        

        return getURL(aux);
        
    }
    
    private String getURL (String subcad)
    {
        return subcad.substring(64,99);
    }
    private String getToken(String subCad) {
        return subCad.substring(45, 54);
    }
}
