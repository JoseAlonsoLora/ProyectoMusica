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
public class Biblioteca {
    private Integer idBiblioteca;
    private short publica;
    private Usuario usuarionombreUsuario;
    private List<Album> albumList;

    public Biblioteca() {
    }

    public Biblioteca(Integer idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public Biblioteca(Integer idBiblioteca, short publica) {
        this.idBiblioteca = idBiblioteca;
        this.publica = publica;
    }

    public Integer getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(Integer idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public short getPublica() {
        return publica;
    }

    public void setPublica(short publica) {
        this.publica = publica;
    }

    public Usuario getUsuarionombreUsuario() {
        return usuarionombreUsuario;
    }

    public void setUsuarionombreUsuario(Usuario usuarionombreUsuario) {
        this.usuarionombreUsuario = usuarionombreUsuario;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}
