/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */
package com.control_notas.model;

/**
 * Clase Persona
 * 
*/
public abstract class Persona extends Usuario{
   
    //Atributos
    int ID_persona;
    String num_identificacion,nombre,apellido;
    
    //Constructor por defecto
    public Persona () {};
    
    //Constructor con parámetros
    public Persona(int ID_persona, String num_identificacion, String nombre, String apellido, int idUsuario, String correo, String clave, String fechaCreacion, int status, int idRol, String rol) {
        super(idUsuario, correo, clave, fechaCreacion, status, idRol, rol);
        this.ID_persona = ID_persona;
        this.num_identificacion = num_identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    //Getters and Setters

    public int getID_persona() {
        return ID_persona;
    }

    public void setID_persona(int ID_persona) {
        this.ID_persona = ID_persona;
    }

    public String getNum_identificacion() {
        return num_identificacion;
    }

    public void setNum_identificacion(String num_identificacion) {
        this.num_identificacion = num_identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

   
    
}
