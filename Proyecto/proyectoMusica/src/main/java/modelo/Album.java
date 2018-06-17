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
public class Album {
    private Integer idalbum;
    private String nombre;
    private String anolanzamiento;
    private String compania;
    private int artista_idartista;
    private int biblioteca_idbiblioteca;
    private int genero_idgenero;
    private List<Cancion> cancionList;

    public Album() {
    }

    public Integer getIdalbum() {
        return idalbum;
    }

    public void setIdalbum(Integer idalbum) {
        this.idalbum = idalbum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnolanzamiento() {
        return anolanzamiento;
    }

    public void setAnolanzamiento(String anolanzamiento) {
        this.anolanzamiento = anolanzamiento;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getArtista_idartista() {
        return artista_idartista;
    }

    public void setArtista_idartista(int artista_idartista) {
        this.artista_idartista = artista_idartista;
    }

    public int getBiblioteca_idbiblioteca() {
        return biblioteca_idbiblioteca;
    }

    public void setBiblioteca_idbiblioteca(int biblioteca_idbiblioteca) {
        this.biblioteca_idbiblioteca = biblioteca_idbiblioteca;
    }

    public int getGenero_idgenero() {
        return genero_idgenero;
    }

    public void setGenero_idgenero(int genero_idgenero) {
        this.genero_idgenero = genero_idgenero;
    }

    
    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    
}
