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
    private Usuario usuario_nombreusuario;

    public Biblioteca() {
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

    public Usuario getUsuario_nombreusuario() {
        return usuario_nombreusuario;
    }

    public void setUsuario_nombreusuario(Usuario usuario_nombreusuario) {
        this.usuario_nombreusuario = usuario_nombreusuario;
    }

    
}
