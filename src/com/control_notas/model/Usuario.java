
package com.control_notas.model;

import com.control_notas.daoimpl.FilterManager;

public class Usuario extends Rol {
    
    //Atributos
    int idUsuario;
    String correo, clave, fechaCreacion;
    int status;
    
    
    //Constructor por defecto
    public Usuario () {};
    
    //Constructor con parametros.

        public Usuario(int idUsuario, String correo, String clave, String fechaCreacion, int status, int idRol, String rol) {
        super(idRol, rol);
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.clave = clave;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
    }
    
    //Getters and Setters

    public int getIdUsuario() {
        return idUsuario;
        //Comentario en getIdUsuario()
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    //Métodos
    FilterManager filterManager;

   public void setFilterManager(FilterManager filterManager){
      this.filterManager = filterManager;
   }
    
   public boolean sendCredentials(String username, String password) throws Exception {
        return filterManager.filterRequest(username, password);
    }
    //Encriptar contraseña
    
    private String encriptarPassword()
    {
        //proceso
        return "Password";
       
    }
   
    
    
    
}
