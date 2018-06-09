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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    , @NamedQuery(name = "Listareproduccion.findByIdlistaReproduccion", query = "SELECT l FROM Listareproduccion l WHERE l.idlistaReproduccion = :idlistaReproduccion")
    , @NamedQuery(name = "Listareproduccion.findByNombre", query = "SELECT l FROM Listareproduccion l WHERE l.nombre = :nombre")})
public class Listareproduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlistaReproduccion")
    private Integer idlistaReproduccion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "listareproduccionList")
    private List<Cancion> cancionList;
    @JoinColumn(name = "usuario_nombreUsuario", referencedColumnName = "nombreUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarionombreUsuario;

    public Listareproduccion() {
    }

    public Listareproduccion(Integer idlistaReproduccion) {
        this.idlistaReproduccion = idlistaReproduccion;
    }

    public Listareproduccion(Integer idlistaReproduccion, String nombre) {
        this.idlistaReproduccion = idlistaReproduccion;
        this.nombre = nombre;
    }

    public Integer getIdlistaReproduccion() {
        return idlistaReproduccion;
    }

    public void setIdlistaReproduccion(Integer idlistaReproduccion) {
        this.idlistaReproduccion = idlistaReproduccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    public Usuario getUsuarionombreUsuario() {
        return usuarionombreUsuario;
    }

    public void setUsuarionombreUsuario(Usuario usuarionombreUsuario) {
        this.usuarionombreUsuario = usuarionombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlistaReproduccion != null ? idlistaReproduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listareproduccion)) {
            return false;
        }
        Listareproduccion other = (Listareproduccion) object;
        if ((this.idlistaReproduccion == null && other.idlistaReproduccion != null) || (this.idlistaReproduccion != null && !this.idlistaReproduccion.equals(other.idlistaReproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Listareproduccion[ idlistaReproduccion=" + idlistaReproduccion + " ]";
    }
    
}
