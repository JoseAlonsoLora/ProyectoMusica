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
    private Integer idbiblioteca;
    private short publica;
    private String usuario_nombreusuario;

    public Biblioteca() {
    }

    public Integer getIdbiblioteca() {
        return idbiblioteca;
    }

    public void setIdbiblioteca(Integer idbiblioteca) {
        this.idbiblioteca = idbiblioteca;
    }
    

    public short getPublica() {
        return publica;
    }

    public void setPublica(short publica) {
        this.publica = publica;
    }

    public String getUsuario_nombreusuario() {
        return usuario_nombreusuario;
    }

    public void setUsuario_nombreusuario(String usuario_nombreusuario) {
        this.usuario_nombreusuario = usuario_nombreusuario;
    }



    
}
