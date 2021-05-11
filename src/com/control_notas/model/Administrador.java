/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */
package com.control_notas.model;

/**
 * Clase Administrador extends Persona
 * 
*/
public class Administrador extends Persona{
    
    //Atributos
    String cargo;
    
    //Constructor por defecto
    public Administrador () {};
    //Constructor con parámetros
    public Administrador(String cargo, int ID_persona, String num_identificacion, String nombre, String apellido, int idUsuario, String correo, String clave, String fechaCreacion, int status, int idRol, String rol) {
        super(ID_persona, num_identificacion, nombre, apellido, idUsuario, correo, clave, fechaCreacion, status, idRol, rol);
        this.cargo = cargo;
    }
    //Getters and Setters

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
}
