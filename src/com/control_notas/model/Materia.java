/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */


package com.control_notas.model;

/**
 * Clase Materia
 * 
*/
public class Materia {
    
    //Atributos
    
    int ID_materia;
    String nombre;
    
    //Constructor por defecto
    
    public Materia () {};
    
    //Constructor con parámetros
    public Materia(int ID_materia, String nombre) {
        this.ID_materia = ID_materia;
        this.nombre = nombre;
    }
    
    //Getters and Setters

    public int getID_materia() {
        return ID_materia;
    }

    public void setID_materia(int ID_materia) {
        this.ID_materia = ID_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
