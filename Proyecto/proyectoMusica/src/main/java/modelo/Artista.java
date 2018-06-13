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
public class Artista {
    private Integer idartista;
    private String nombre;
    private List<Album> albumList;

    public Integer getIdartista() {
        return idartista;
    }

    public void setIdartista(Integer idartista) {
        this.idartista = idartista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

  

    
}
