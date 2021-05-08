/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */

/**
 * Clase Rol.
 * 
*/
package com.control_notas.model;

public class Rol {
    
    //Atributos
    
    int idRol;
    String rol;
    
    //Constructor por defecto
    
    public Rol () {};

    public Rol(int idRol, String rol) {
        this.idRol = idRol;
        this.rol = rol;
    }
    
    //Comentario de Prueba
    
    //Comentario de prueba VOLKMAR

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
