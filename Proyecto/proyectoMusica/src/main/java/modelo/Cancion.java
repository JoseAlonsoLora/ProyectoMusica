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
public class Cancion {
    private Integer idcancion;
    private String nombre;
    private Integer calificacion;
    private String nombrearchivo;
    private Album album_idalbum;

    public Cancion() {
    }

    public Integer getIdcancion() {
        return idcancion;
    }

    public void setIdcancion(Integer idcancion) {
        this.idcancion = idcancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getNombrearchivo() {
        return nombrearchivo;
    }

    public void setNombrearchivo(String nombrearchivo) {
        this.nombrearchivo = nombrearchivo;
    }

    public Album getAlbum_idalbum() {
        return album_idalbum;
    }

    public void setAlbum_idalbum(Album album_idalbum) {
        this.album_idalbum = album_idalbum;
    }


}
