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
@Table(name = "biblioteca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biblioteca.findAll", query = "SELECT b FROM Biblioteca b")
    , @NamedQuery(name = "Biblioteca.findByIdBiblioteca", query = "SELECT b FROM Biblioteca b WHERE b.bibliotecaPK.idBiblioteca = :idBiblioteca")
    , @NamedQuery(name = "Biblioteca.findByUsuarionombreUsuario", query = "SELECT b FROM Biblioteca b WHERE b.bibliotecaPK.usuarionombreUsuario = :usuarionombreUsuario")
    , @NamedQuery(name = "Biblioteca.findByPublica", query = "SELECT b FROM Biblioteca b WHERE b.publica = :publica")})
public class Biblioteca implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BibliotecaPK bibliotecaPK;
    @Basic(optional = false)
    @Column(name = "publica")
    private short publica;
    @JoinColumn(name = "usuario_nombreUsuario", referencedColumnName = "nombreUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biblioteca")
    private List<Album> albumList;

    public Biblioteca() {
    }

    public Biblioteca(BibliotecaPK bibliotecaPK) {
        this.bibliotecaPK = bibliotecaPK;
    }

    public Biblioteca(BibliotecaPK bibliotecaPK, short publica) {
        this.bibliotecaPK = bibliotecaPK;
        this.publica = publica;
    }

    public Biblioteca(int idBiblioteca, String usuarionombreUsuario) {
        this.bibliotecaPK = new BibliotecaPK(idBiblioteca, usuarionombreUsuario);
    }

    public BibliotecaPK getBibliotecaPK() {
        return bibliotecaPK;
    }

    public void setBibliotecaPK(BibliotecaPK bibliotecaPK) {
        this.bibliotecaPK = bibliotecaPK;
    }

    public short getPublica() {
        return publica;
    }

    public void setPublica(short publica) {
        this.publica = publica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bibliotecaPK != null ? bibliotecaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biblioteca)) {
            return false;
        }
        Biblioteca other = (Biblioteca) object;
        if ((this.bibliotecaPK == null && other.bibliotecaPK != null) || (this.bibliotecaPK != null && !this.bibliotecaPK.equals(other.bibliotecaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Biblioteca[ bibliotecaPK=" + bibliotecaPK + " ]";
    }
    
}
