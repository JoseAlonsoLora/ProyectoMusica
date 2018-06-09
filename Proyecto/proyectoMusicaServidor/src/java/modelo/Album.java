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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author raymundo
 */
@Entity
@Table(name = "album")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a")
    , @NamedQuery(name = "Album.findByIdAlbum", query = "SELECT a FROM Album a WHERE a.idAlbum = :idAlbum")
    , @NamedQuery(name = "Album.findByNombre", query = "SELECT a FROM Album a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Album.findByAnoLanzamiento", query = "SELECT a FROM Album a WHERE a.anoLanzamiento = :anoLanzamiento")
    , @NamedQuery(name = "Album.findByCompania", query = "SELECT a FROM Album a WHERE a.compania = :compania")})
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAlbum")
    private Integer idAlbum;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "anoLanzamiento")
    private String anoLanzamiento;
    @Column(name = "compania")
    private String compania;
    @JoinColumn(name = "artista_idArtista", referencedColumnName = "idArtista")
    @ManyToOne(optional = false)
    private Artista artistaidArtista;
    @JoinColumn(name = "biblioteca_idBiblioteca", referencedColumnName = "idBiblioteca")
    @ManyToOne(optional = false)
    private Biblioteca bibliotecaidBiblioteca;
    @JoinColumn(name = "genero_idGenero", referencedColumnName = "idGenero")
    @ManyToOne(optional = false)
    private Genero generoidGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "albumidAlbum")
    private List<Cancion> cancionList;

    public Album() {
    }

    public Album(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Album(Integer idAlbum, String nombre) {
        this.idAlbum = idAlbum;
        this.nombre = nombre;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
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

    public Artista getArtistaidArtista() {
        return artistaidArtista;
    }

    public void setArtistaidArtista(Artista artistaidArtista) {
        this.artistaidArtista = artistaidArtista;
    }

    public Biblioteca getBibliotecaidBiblioteca() {
        return bibliotecaidBiblioteca;
    }

    public void setBibliotecaidBiblioteca(Biblioteca bibliotecaidBiblioteca) {
        this.bibliotecaidBiblioteca = bibliotecaidBiblioteca;
    }

    public Genero getGeneroidGenero() {
        return generoidGenero;
    }

    public void setGeneroidGenero(Genero generoidGenero) {
        this.generoidGenero = generoidGenero;
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
        hash += (idAlbum != null ? idAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.idAlbum == null && other.idAlbum != null) || (this.idAlbum != null && !this.idAlbum.equals(other.idAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Album[ idAlbum=" + idAlbum + " ]";
    }
    
}
