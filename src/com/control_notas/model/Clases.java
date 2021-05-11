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
public class Clases extends Docente {
    //Atributos
    int ID_clases, semestre_clase;
    String cod_carrera;
    
    
    
    //Constructor por defecto
    public Clases () {};
    //Constructor con parámetros

    public Clases(int ID_clases, String cod_carrera, String cargo, int ID_persona, String num_identificacion, String nombre, String apellido, int idUsuario, String correo, String clave, String fechaCreacion, int status, int idRol, String rol, int semestre_clase) {
        super(cargo, ID_persona, num_identificacion, nombre, apellido, idUsuario, correo, clave, fechaCreacion, status, idRol, rol);
        this.ID_clases = ID_clases;
        this.cod_carrera = cod_carrera;
        this.semestre_clase = semestre_clase;
    }
    
    //Getters and Setters
    public int getSemestre_clase() {
        return semestre_clase;
    }

    public void setSemestre_clase(int semestre_clase) {
        this.semestre_clase = semestre_clase;
    }

    public int getID_clases() {
        return ID_clases;
    }

    public void setID_clases(int ID_clases) {
        this.ID_clases = ID_clases;
    }

    public String getCod_carrera() {
        return cod_carrera;
    }

    public void setCod_carrera(String cod_carrera) {
        this.cod_carrera = cod_carrera;
    }
    
}
