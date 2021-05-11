/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */

package com.control_notas.model;

/**
 * Clase Carrera
 * 
*/
public class Carrera extends Materia{
    
    //Atributos
    String cod_carrera, nombre_carrera, facultad;
    
    //Constructor por defecto
    public Carrera () {};
    
    //Constructor con parámetros
     public Carrera(String cod_carrera, String nombre_carrera, String facultad, int ID_materia, String nombre) {
        super(ID_materia, nombre);
        this.cod_carrera = cod_carrera;
        this.nombre_carrera = nombre_carrera;
        this.facultad = facultad;
    }
    //Getters and Setters

    public String getCod_carrera() {
        return cod_carrera;
    }

    public void setCod_carrera(String cod_carrera) {
        this.cod_carrera = cod_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }



   

   
}
