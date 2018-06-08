/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author raymundo
 */
@Entity
@Table(name = "listareproduccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listareproduccion.findAll", query = "SELECT l FROM Listareproduccion l")
    , @NamedQuery(name = "Listareproduccion.findByIdlistaReproduccion", query = "SELECT l FROM Listareproduccion l WHERE l.listareproduccionPK.idlistaReproduccion = :idlistaReproduccion")
    , @NamedQuery(name = "Listareproduccion.findByNombre", query = "SELECT l FROM Listareproduccion l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Listareproduccion.findByUsuarionombreUsuario", query = "SELECT l FROM Listareproduccion l WHERE l.listareproduccionPK.usuarionombreUsuario = :usuarionombreUsuario")})
public class Listareproduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ListareproduccionPK listareproduccionPK;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "listareproduccionList")
    private List<Cancion> cancionList;
    @JoinColumn(name = "usuario_nombreUsuario", referencedColumnName = "nombreUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Listareproduccion() {
    }

    public Listareproduccion(ListareproduccionPK listareproduccionPK) {
        this.listareproduccionPK = listareproduccionPK;
    }

    public Listareproduccion(ListareproduccionPK listareproduccionPK, String nombre) {
        this.listareproduccionPK = listareproduccionPK;
        this.nombre = nombre;
    }

    public Listareproduccion(int idlistaReproduccion, String usuarionombreUsuario) {
        this.listareproduccionPK = new ListareproduccionPK(idlistaReproduccion, usuarionombreUsuario);
    }

    public ListareproduccionPK getListareproduccionPK() {
        return listareproduccionPK;
    }

    public void setListareproduccionPK(ListareproduccionPK listareproduccionPK) {
        this.listareproduccionPK = listareproduccionPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listareproduccionPK != null ? listareproduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listareproduccion)) {
            return false;
        }
        Listareproduccion other = (Listareproduccion) object;
        if ((this.listareproduccionPK == null && other.listareproduccionPK != null) || (this.listareproduccionPK != null && !this.listareproduccionPK.equals(other.listareproduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Listareproduccion[ listareproduccionPK=" + listareproduccionPK + " ]";
    }
    
}
