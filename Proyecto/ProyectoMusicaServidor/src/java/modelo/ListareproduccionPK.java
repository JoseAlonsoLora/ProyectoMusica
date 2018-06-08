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
 * @author raymundo
 */
@Embeddable
public class ListareproduccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idlistaReproduccion")
    private int idlistaReproduccion;
    @Basic(optional = false)
    @Column(name = "usuario_nombreUsuario")
    private String usuarionombreUsuario;

    public ListareproduccionPK() {
    }

    public ListareproduccionPK(int idlistaReproduccion, String usuarionombreUsuario) {
        this.idlistaReproduccion = idlistaReproduccion;
        this.usuarionombreUsuario = usuarionombreUsuario;
    }

    public int getIdlistaReproduccion() {
        return idlistaReproduccion;
    }

    public void setIdlistaReproduccion(int idlistaReproduccion) {
        this.idlistaReproduccion = idlistaReproduccion;
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
        hash += (int) idlistaReproduccion;
        hash += (usuarionombreUsuario != null ? usuarionombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListareproduccionPK)) {
            return false;
        }
        ListareproduccionPK other = (ListareproduccionPK) object;
        if (this.idlistaReproduccion != other.idlistaReproduccion) {
            return false;
        }
        if ((this.usuarionombreUsuario == null && other.usuarionombreUsuario != null) || (this.usuarionombreUsuario != null && !this.usuarionombreUsuario.equals(other.usuarionombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ListareproduccionPK[ idlistaReproduccion=" + idlistaReproduccion + ", usuarionombreUsuario=" + usuarionombreUsuario + " ]";
    }
    
}
