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
import javax.persistence.JoinColumns;
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
    , @NamedQuery(name = "Cancion.findByIdCancion", query = "SELECT c FROM Cancion c WHERE c.cancionPK.idCancion = :idCancion")
    , @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cancion.findByCalificacion", query = "SELECT c FROM Cancion c WHERE c.calificacion = :calificacion")
    , @NamedQuery(name = "Cancion.findByNombreArchivo", query = "SELECT c FROM Cancion c WHERE c.nombreArchivo = :nombreArchivo")
    , @NamedQuery(name = "Cancion.findByAlbumidAlbum", query = "SELECT c FROM Cancion c WHERE c.cancionPK.albumidAlbum = :albumidAlbum")
    , @NamedQuery(name = "Cancion.findByAlbumartistaidArtista", query = "SELECT c FROM Cancion c WHERE c.cancionPK.albumartistaidArtista = :albumartistaidArtista")
    , @NamedQuery(name = "Cancion.findByAlbumgeneroidGenero", query = "SELECT c FROM Cancion c WHERE c.cancionPK.albumgeneroidGenero = :albumgeneroidGenero")
    , @NamedQuery(name = "Cancion.findByAlbumbibliotecaidBiblioteca", query = "SELECT c FROM Cancion c WHERE c.cancionPK.albumbibliotecaidBiblioteca = :albumbibliotecaidBiblioteca")
    , @NamedQuery(name = "Cancion.findByAlbumbibliotecausuarionombreUsuario", query = "SELECT c FROM Cancion c WHERE c.cancionPK.albumbibliotecausuarionombreUsuario = :albumbibliotecausuarionombreUsuario")})
public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CancionPK cancionPK;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Basic(optional = false)
    @Column(name = "nombreArchivo")
    private String nombreArchivo;
    @JoinTable(name = "listareproduccion_has_cancion", joinColumns = {
        @JoinColumn(name = "cancion_idCancion", referencedColumnName = "idCancion")
        , @JoinColumn(name = "cancion_album_idAlbum", referencedColumnName = "album_idAlbum")
        , @JoinColumn(name = "cancion_album_artista_idArtista", referencedColumnName = "album_artista_idArtista")
        , @JoinColumn(name = "cancion_album_genero_idGenero", referencedColumnName = "album_genero_idGenero")
        , @JoinColumn(name = "cancion_album_biblioteca_idBiblioteca", referencedColumnName = "album_biblioteca_idBiblioteca")
        , @JoinColumn(name = "cancion_album_biblioteca_usuario_nombreUsuario", referencedColumnName = "album_biblioteca_usuario_nombreUsuario")}, inverseJoinColumns = {
        @JoinColumn(name = "listaReproduccion_idlistaReproduccion", referencedColumnName = "idlistaReproduccion")
        , @JoinColumn(name = "listaReproduccion_usuario_nombreUsuario", referencedColumnName = "usuario_nombreUsuario")})
    @ManyToMany
    private List<Listareproduccion> listareproduccionList;
    @JoinColumns({
        @JoinColumn(name = "album_idAlbum", referencedColumnName = "idAlbum", insertable = false, updatable = false)
        , @JoinColumn(name = "album_artista_idArtista", referencedColumnName = "artista_idArtista", insertable = false, updatable = false)
        , @JoinColumn(name = "album_genero_idGenero", referencedColumnName = "genero_idGenero", insertable = false, updatable = false)
        , @JoinColumn(name = "album_biblioteca_idBiblioteca", referencedColumnName = "biblioteca_idBiblioteca", insertable = false, updatable = false)
        , @JoinColumn(name = "album_biblioteca_usuario_nombreUsuario", referencedColumnName = "biblioteca_usuario_nombreUsuario", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Album album;

    public Cancion() {
    }

    public Cancion(CancionPK cancionPK) {
        this.cancionPK = cancionPK;
    }

    public Cancion(CancionPK cancionPK, String nombre, String nombreArchivo) {
        this.cancionPK = cancionPK;
        this.nombre = nombre;
        this.nombreArchivo = nombreArchivo;
    }

    public Cancion(int idCancion, int albumidAlbum, int albumartistaidArtista, int albumgeneroidGenero, int albumbibliotecaidBiblioteca, String albumbibliotecausuarionombreUsuario) {
        this.cancionPK = new CancionPK(idCancion, albumidAlbum, albumartistaidArtista, albumgeneroidGenero, albumbibliotecaidBiblioteca, albumbibliotecausuarionombreUsuario);
    }

    public CancionPK getCancionPK() {
        return cancionPK;
    }

    public void setCancionPK(CancionPK cancionPK) {
        this.cancionPK = cancionPK;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cancionPK != null ? cancionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.cancionPK == null && other.cancionPK != null) || (this.cancionPK != null && !this.cancionPK.equals(other.cancionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cancion[ cancionPK=" + cancionPK + " ]";
    }
    
}
