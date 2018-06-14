/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author raymundo
 */
public class Listareproduccion {
    private Integer idlistareproduccion;
    private String nombre;
    private String usuario_nombreusuario;

    public Listareproduccion() {
    }

    public Integer getIdlistareproduccion() {
        return idlistareproduccion;
    }

    public void setIdlistareproduccion(Integer idlistareproduccion) {
        this.idlistareproduccion = idlistareproduccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario_nombreusuario() {
        return usuario_nombreusuario;
    }

    public void setUsuario_nombreusuario(String usuario_nombreusuario) {
        this.usuario_nombreusuario = usuario_nombreusuario;
    }


}
