/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */
package com.control_notas.model;

/**
 * Clase Estudiante extends Persona
 * 
*/
public class Estudiante extends Persona{
    //Atributos
    
    int num_semestre;
    String cod_carrera;
    
    //Constructor por defecto
    public Estudiante () {};
    //Constructor con parámetros
     public Estudiante(int num_semestre, int ID_persona, String num_identificacion, String nombre, String apellido, int idUsuario, String correo, String clave, String fechaCreacion, int status, int idRol, String rol, String cod_carrera) {
        super(ID_persona, num_identificacion, nombre, apellido, idUsuario, correo, clave, fechaCreacion, status, idRol, rol);
        this.num_semestre = num_semestre;
        this.cod_carrera = cod_carrera;
    }
    
    //Getters and Setters

    public int getNum_semestre() {
        return num_semestre;
    }

    public void setNum_semestre(int num_semestre) {
        this.num_semestre = num_semestre;
    }

    public String getCod_carrera() {
        return cod_carrera;
    }

    public void setCod_carrera(String cod_carrera) {
        this.cod_carrera = cod_carrera;
    }
    
    
    

   
    
    
}
