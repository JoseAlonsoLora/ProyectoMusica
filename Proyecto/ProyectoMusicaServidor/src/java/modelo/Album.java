/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Irdevelo
 */
@Entity
@Table(name = "album")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a")
    , @NamedQuery(name = "Album.findByIdAlbum", query = "SELECT a FROM Album a WHERE a.albumPK.idAlbum = :idAlbum")
    , @NamedQuery(name = "Album.findByNombre", query = "SELECT a FROM Album a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Album.findByAnoLanzamiento", query = "SELECT a FROM Album a WHERE a.anoLanzamiento = :anoLanzamiento")
    , @NamedQuery(name = "Album.findByCompania", query = "SELECT a FROM Album a WHERE a.compania = :compania")
    , @NamedQuery(name = "Album.findByArtistaidArtista", query = "SELECT a FROM Album a WHERE a.albumPK.artistaidArtista = :artistaidArtista")
    , @NamedQuery(name = "Album.findByGeneroidGenero", query = "SELECT a FROM Album a WHERE a.albumPK.generoidGenero = :generoidGenero")
    , @NamedQuery(name = "Album.findByBibliotecaidBiblioteca", query = "SELECT a FROM Album a WHERE a.albumPK.bibliotecaidBiblioteca = :bibliotecaidBiblioteca")
    , @NamedQuery(name = "Album.findByBibliotecausuarionombreUsuario", query = "SELECT a FROM Album a WHERE a.albumPK.bibliotecausuarionombreUsuario = :bibliotecausuarionombreUsuario")})
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlbumPK albumPK;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "anoLanzamiento")
    private String anoLanzamiento;
    @Column(name = "compania")
    private String compania;
    @JoinColumn(name = "artista_idArtista", referencedColumnName = "idArtista", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artista artista;
    @JoinColumn(name = "genero_idGenero", referencedColumnName = "idGenero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genero genero;
    @JoinColumns({
        @JoinColumn(name = "biblioteca_idBiblioteca", referencedColumnName = "idBiblioteca", insertable = false, updatable = false)
        , @JoinColumn(name = "biblioteca_usuario_nombreUsuario", referencedColumnName = "usuario_nombreUsuario", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Biblioteca biblioteca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<Cancion> cancionList;

    public Album() {
    }

    public Album(AlbumPK albumPK) {
        this.albumPK = albumPK;
    }

    public Album(AlbumPK albumPK, String nombre) {
        this.albumPK = albumPK;
        this.nombre = nombre;
    }

    public Album(int idAlbum, int artistaidArtista, int generoidGenero, int bibliotecaidBiblioteca, String bibliotecausuarionombreUsuario) {
        this.albumPK = new AlbumPK(idAlbum, artistaidArtista, generoidGenero, bibliotecaidBiblioteca, bibliotecausuarionombreUsuario);
    }

    public AlbumPK getAlbumPK() {
        return albumPK;
    }

    public void setAlbumPK(AlbumPK albumPK) {
        this.albumPK = albumPK;
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @XmlTransient
    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumPK != null ? albumPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.albumPK == null && other.albumPK != null) || (this.albumPK != null && !this.albumPK.equals(other.albumPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Album[ albumPK=" + albumPK + " ]";
    }
    
}
