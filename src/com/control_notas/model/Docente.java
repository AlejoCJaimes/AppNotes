/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */
package com.control_notas.model;

/**
 * Clase Docente extends Persona
 * 
*/
public class Docente extends Persona{
    
    //Atributos
     String titulo_docente;
    
    //Constructor por defecto
    public Docente () {};
    //Constructor con parámetros
    public Docente(String titulo_docente, int ID_persona, String num_identificacion, String nombre, String apellido, int idUsuario, String correo, String clave, String fechaCreacion, int status, int idRol, String rol) {
        super(ID_persona, num_identificacion, nombre, apellido, idUsuario, correo, clave, fechaCreacion, status, idRol, rol);
        this.titulo_docente = titulo_docente;
    }
    //Getters and Setters

    public String getTituloDocente() {
        return titulo_docente;
    }

    public void setTituloDocente(String titulo_docente) {
        this.titulo_docente = titulo_docente;
    }
    
    
}
