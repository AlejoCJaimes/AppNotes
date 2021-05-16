/**
 * @Model or the transfer object
 * Which holds oour data and is transferred
 * from a lawyer to another.
 */
package com.control_notas.model;

/**
 * Clase Notas
 * 
*/
public class Notas extends Clases{
  //Atributos
    int ID_nota;
    float corte,calificacion;
    
    //Atributos auxiliares
    Carrera carrera;
    //Constructor por defecto
    public Notas() {};
    
    //Constructor con parámetros

    public Notas(int ID_nota, float corte, float calificacion, int ID_clases, String cod_carrera, String cargo, int ID_persona, String num_identificacion, String nombre, String apellido, int idUsuario, String correo, String clave, String fechaCreacion, int status, int idRol, String rol, int semestre_clase) {
        super(ID_clases, cod_carrera, cargo, ID_persona, num_identificacion, nombre, apellido, idUsuario, correo, clave, fechaCreacion, status, idRol, rol, semestre_clase);
        this.ID_nota = ID_nota;
        this.corte = corte;
        this.calificacion = calificacion;
    }
    //Getters and setters especiales

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    //Getters and Setters
    public int getID_nota() {
        return ID_nota;
    }

    public void setID_nota(int ID_nota) {
        this.ID_nota = ID_nota;
    }

    public float getCorte() {
        return corte;
    }

    public void setCorte(float corte) {
        this.corte = corte;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    
    
    
}
