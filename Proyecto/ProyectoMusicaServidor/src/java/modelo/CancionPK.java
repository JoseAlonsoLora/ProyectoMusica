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
public class CancionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idCancion")
    private int idCancion;
    @Basic(optional = false)
    @Column(name = "album_idAlbum")
    private int albumidAlbum;
    @Basic(optional = false)
    @Column(name = "album_artista_idArtista")
    private int albumartistaidArtista;
    @Basic(optional = false)
    @Column(name = "album_genero_idGenero")
    private int albumgeneroidGenero;
    @Basic(optional = false)
    @Column(name = "album_biblioteca_idBiblioteca")
    private int albumbibliotecaidBiblioteca;
    @Basic(optional = false)
    @Column(name = "album_biblioteca_usuario_nombreUsuario")
    private String albumbibliotecausuarionombreUsuario;

    public CancionPK() {
    }

    public CancionPK(int idCancion, int albumidAlbum, int albumartistaidArtista, int albumgeneroidGenero, int albumbibliotecaidBiblioteca, String albumbibliotecausuarionombreUsuario) {
        this.idCancion = idCancion;
        this.albumidAlbum = albumidAlbum;
        this.albumartistaidArtista = albumartistaidArtista;
        this.albumgeneroidGenero = albumgeneroidGenero;
        this.albumbibliotecaidBiblioteca = albumbibliotecaidBiblioteca;
        this.albumbibliotecausuarionombreUsuario = albumbibliotecausuarionombreUsuario;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getAlbumidAlbum() {
        return albumidAlbum;
    }

    public void setAlbumidAlbum(int albumidAlbum) {
        this.albumidAlbum = albumidAlbum;
    }

    public int getAlbumartistaidArtista() {
        return albumartistaidArtista;
    }

    public void setAlbumartistaidArtista(int albumartistaidArtista) {
        this.albumartistaidArtista = albumartistaidArtista;
    }

    public int getAlbumgeneroidGenero() {
        return albumgeneroidGenero;
    }

    public void setAlbumgeneroidGenero(int albumgeneroidGenero) {
        this.albumgeneroidGenero = albumgeneroidGenero;
    }

    public int getAlbumbibliotecaidBiblioteca() {
        return albumbibliotecaidBiblioteca;
    }

    public void setAlbumbibliotecaidBiblioteca(int albumbibliotecaidBiblioteca) {
        this.albumbibliotecaidBiblioteca = albumbibliotecaidBiblioteca;
    }

    public String getAlbumbibliotecausuarionombreUsuario() {
        return albumbibliotecausuarionombreUsuario;
    }

    public void setAlbumbibliotecausuarionombreUsuario(String albumbibliotecausuarionombreUsuario) {
        this.albumbibliotecausuarionombreUsuario = albumbibliotecausuarionombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCancion;
        hash += (int) albumidAlbum;
        hash += (int) albumartistaidArtista;
        hash += (int) albumgeneroidGenero;
        hash += (int) albumbibliotecaidBiblioteca;
        hash += (albumbibliotecausuarionombreUsuario != null ? albumbibliotecausuarionombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CancionPK)) {
            return false;
        }
        CancionPK other = (CancionPK) object;
        if (this.idCancion != other.idCancion) {
            return false;
        }
        if (this.albumidAlbum != other.albumidAlbum) {
            return false;
        }
        if (this.albumartistaidArtista != other.albumartistaidArtista) {
            return false;
        }
        if (this.albumgeneroidGenero != other.albumgeneroidGenero) {
            return false;
        }
        if (this.albumbibliotecaidBiblioteca != other.albumbibliotecaidBiblioteca) {
            return false;
        }
        if ((this.albumbibliotecausuarionombreUsuario == null && other.albumbibliotecausuarionombreUsuario != null) || (this.albumbibliotecausuarionombreUsuario != null && !this.albumbibliotecausuarionombreUsuario.equals(other.albumbibliotecausuarionombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CancionPK[ idCancion=" + idCancion + ", albumidAlbum=" + albumidAlbum + ", albumartistaidArtista=" + albumartistaidArtista + ", albumgeneroidGenero=" + albumgeneroidGenero + ", albumbibliotecaidBiblioteca=" + albumbibliotecaidBiblioteca + ", albumbibliotecausuarionombreUsuario=" + albumbibliotecausuarionombreUsuario + " ]";
    }
    
}
