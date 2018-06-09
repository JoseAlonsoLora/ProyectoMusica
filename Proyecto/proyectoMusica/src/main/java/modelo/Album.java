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
    private Integer idAlbum;
    private String nombre;
    private String anoLanzamiento;
    private String compania;
    private Artista artistaidArtista;
    private Biblioteca bibliotecaidBiblioteca;
    private Genero generoidGenero;
    private List<Cancion> cancionList;

    public Album() {
    }

    public Album(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Album(Integer idAlbum, String nombre) {
        this.idAlbum = idAlbum;
        this.nombre = nombre;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(String anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Artista getArtistaidArtista() {
        return artistaidArtista;
    }

    public void setArtistaidArtista(Artista artistaidArtista) {
        this.artistaidArtista = artistaidArtista;
    }

    public Biblioteca getBibliotecaidBiblioteca() {
        return bibliotecaidBiblioteca;
    }

    public void setBibliotecaidBiblioteca(Biblioteca bibliotecaidBiblioteca) {
        this.bibliotecaidBiblioteca = bibliotecaidBiblioteca;
    }

    public Genero getGeneroidGenero() {
        return generoidGenero;
    }

    public void setGeneroidGenero(Genero generoidGenero) {
        this.generoidGenero = generoidGenero;
    }

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }
}
