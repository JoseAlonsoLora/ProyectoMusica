/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author raymundo
 */
public class Listareproduccion {
    private Integer idlistaReproduccion;
    private String nombre;
    private List<Cancion> cancionList;
    private Usuario usuarionombreUsuario;

    public Listareproduccion() {
    }

    public Listareproduccion(Integer idlistaReproduccion) {
        this.idlistaReproduccion = idlistaReproduccion;
    }

    public Listareproduccion(Integer idlistaReproduccion, String nombre) {
        this.idlistaReproduccion = idlistaReproduccion;
        this.nombre = nombre;
    }

    public Integer getIdlistaReproduccion() {
        return idlistaReproduccion;
    }

    public void setIdlistaReproduccion(Integer idlistaReproduccion) {
        this.idlistaReproduccion = idlistaReproduccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    public Usuario getUsuarionombreUsuario() {
        return usuarionombreUsuario;
    }

    public void setUsuarionombreUsuario(Usuario usuarionombreUsuario) {
        this.usuarionombreUsuario = usuarionombreUsuario;
    }
}
