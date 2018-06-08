/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Irdevelo
 */
@Embeddable
public class BibliotecaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idBiblioteca")
    private int idBiblioteca;
    @Basic(optional = false)
    @Column(name = "usuario_nombreUsuario")
    private String usuarionombreUsuario;

    public BibliotecaPK() {
    }

    public BibliotecaPK(int idBiblioteca, String usuarionombreUsuario) {
        this.idBiblioteca = idBiblioteca;
        this.usuarionombreUsuario = usuarionombreUsuario;
    }

    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public String getUsuarionombreUsuario() {
        return usuarionombreUsuario;
    }

    public void setUsuarionombreUsuario(String usuarionombreUsuario) {
        this.usuarionombreUsuario = usuarionombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idBiblioteca;
        hash += (usuarionombreUsuario != null ? usuarionombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BibliotecaPK)) {
            return false;
        }
        BibliotecaPK other = (BibliotecaPK) object;
        if (this.idBiblioteca != other.idBiblioteca) {
            return false;
        }
        if ((this.usuarionombreUsuario == null && other.usuarionombreUsuario != null) || (this.usuarionombreUsuario != null && !this.usuarionombreUsuario.equals(other.usuarionombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.BibliotecaPK[ idBiblioteca=" + idBiblioteca + ", usuarionombreUsuario=" + usuarionombreUsuario + " ]";
    }
    
}
