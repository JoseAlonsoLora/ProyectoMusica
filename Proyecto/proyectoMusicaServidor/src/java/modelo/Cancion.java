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
import javax.persistence.JoinTable;
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
@Table(name = "cancion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c")
    , @NamedQuery(name = "Cancion.findByIdCancion", query = "SELECT c FROM Cancion c WHERE c.idCancion = :idCancion")
    , @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cancion.findByCalificacion", query = "SELECT c FROM Cancion c WHERE c.calificacion = :calificacion")
    , @NamedQuery(name = "Cancion.findByNombreArchivo", query = "SELECT c FROM Cancion c WHERE c.nombreArchivo = :nombreArchivo")})
public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCancion")
    private Integer idCancion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Basic(optional = false)
    @Column(name = "nombreArchivo")
    private String nombreArchivo;
    @JoinTable(name = "listaReproduccion_has_cancion", joinColumns = {
        @JoinColumn(name = "cancion_idCancion", referencedColumnName = "idCancion")}, inverseJoinColumns = {
        @JoinColumn(name = "listaReproduccion_idlistaReproduccion", referencedColumnName = "idlistaReproduccion")})
    @ManyToMany
    private List<Listareproduccion> listareproduccionList;
    @JoinColumn(name = "album_idAlbum", referencedColumnName = "idAlbum")
    @ManyToOne(optional = false)
    private Album albumidAlbum;

    public Cancion() {
    }

    public Cancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public Cancion(Integer idCancion, String nombre, String nombreArchivo) {
        this.idCancion = idCancion;
        this.nombre = nombre;
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @XmlTransient
    public List<Listareproduccion> getListareproduccionList() {
        return listareproduccionList;
    }

    public void setListareproduccionList(List<Listareproduccion> listareproduccionList) {
        this.listareproduccionList = listareproduccionList;
    }

    public Album getAlbumidAlbum() {
        return albumidAlbum;
    }

    public void setAlbumidAlbum(Album albumidAlbum) {
        this.albumidAlbum = albumidAlbum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCancion != null ? idCancion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.idCancion == null && other.idCancion != null) || (this.idCancion != null && !this.idCancion.equals(other.idCancion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cancion[ idCancion=" + idCancion + " ]";
    }
    
}
