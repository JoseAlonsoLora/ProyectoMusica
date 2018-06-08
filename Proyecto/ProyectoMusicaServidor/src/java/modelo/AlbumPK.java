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
public class AlbumPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idAlbum")
    private int idAlbum;
    @Basic(optional = false)
    @Column(name = "artista_idArtista")
    private int artistaidArtista;
    @Basic(optional = false)
    @Column(name = "genero_idGenero")
    private int generoidGenero;
    @Basic(optional = false)
    @Column(name = "biblioteca_idBiblioteca")
    private int bibliotecaidBiblioteca;
    @Basic(optional = false)
    @Column(name = "biblioteca_usuario_nombreUsuario")
    private String bibliotecausuarionombreUsuario;

    public AlbumPK() {
    }

    public AlbumPK(int idAlbum, int artistaidArtista, int generoidGenero, int bibliotecaidBiblioteca, String bibliotecausuarionombreUsuario) {
        this.idAlbum = idAlbum;
        this.artistaidArtista = artistaidArtista;
        this.generoidGenero = generoidGenero;
        this.bibliotecaidBiblioteca = bibliotecaidBiblioteca;
        this.bibliotecausuarionombreUsuario = bibliotecausuarionombreUsuario;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getArtistaidArtista() {
        return artistaidArtista;
    }

    public void setArtistaidArtista(int artistaidArtista) {
        this.artistaidArtista = artistaidArtista;
    }

    public int getGeneroidGenero() {
        return generoidGenero;
    }

    public void setGeneroidGenero(int generoidGenero) {
        this.generoidGenero = generoidGenero;
    }

    public int getBibliotecaidBiblioteca() {
        return bibliotecaidBiblioteca;
    }

    public void setBibliotecaidBiblioteca(int bibliotecaidBiblioteca) {
        this.bibliotecaidBiblioteca = bibliotecaidBiblioteca;
    }

    public String getBibliotecausuarionombreUsuario() {
        return bibliotecausuarionombreUsuario;
    }

    public void setBibliotecausuarionombreUsuario(String bibliotecausuarionombreUsuario) {
        this.bibliotecausuarionombreUsuario = bibliotecausuarionombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAlbum;
        hash += (int) artistaidArtista;
        hash += (int) generoidGenero;
        hash += (int) bibliotecaidBiblioteca;
        hash += (bibliotecausuarionombreUsuario != null ? bibliotecausuarionombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlbumPK)) {
            return false;
        }
        AlbumPK other = (AlbumPK) object;
        if (this.idAlbum != other.idAlbum) {
            return false;
        }
        if (this.artistaidArtista != other.artistaidArtista) {
            return false;
        }
        if (this.generoidGenero != other.generoidGenero) {
            return false;
        }
        if (this.bibliotecaidBiblioteca != other.bibliotecaidBiblioteca) {
            return false;
        }
        if ((this.bibliotecausuarionombreUsuario == null && other.bibliotecausuarionombreUsuario != null) || (this.bibliotecausuarionombreUsuario != null && !this.bibliotecausuarionombreUsuario.equals(other.bibliotecausuarionombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AlbumPK[ idAlbum=" + idAlbum + ", artistaidArtista=" + artistaidArtista + ", generoidGenero=" + generoidGenero + ", bibliotecaidBiblioteca=" + bibliotecaidBiblioteca + ", bibliotecausuarionombreUsuario=" + bibliotecausuarionombreUsuario + " ]";
    }
    
}
