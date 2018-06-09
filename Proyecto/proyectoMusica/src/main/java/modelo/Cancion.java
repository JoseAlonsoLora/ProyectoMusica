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
    private Integer idCancion;
    private String nombre;
    private Integer calificacion;
    private String nombreArchivo;
    private List<Listareproduccion> listareproduccionList;
    private Album albumidAlbum;

    public Cancion() {
    }

    public Cancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public Cancion(Integer idCancion, String nombre, String nombreArchivo) {
        this.idCancion = idCancion;
        this.nombre = nombre;
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Integer idCancion) {
        this.idCancion = idCancion;
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public List<Listareproduccion> getListareproduccionList() {
        return listareproduccionList;
    }

    public void setListareproduccionList(List<Listareproduccion> listareproduccionList) {
        this.listareproduccionList = listareproduccionList;
    }

    public Album getAlbumidAlbum() {
        return albumidAlbum;
    }

    public void setAlbumidAlbum(Album albumidAlbum) {
        this.albumidAlbum = albumidAlbum;
    }
}
