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
@Table(name = "biblioteca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biblioteca.findAll", query = "SELECT b FROM Biblioteca b")
    , @NamedQuery(name = "Biblioteca.findByIdBiblioteca", query = "SELECT b FROM Biblioteca b WHERE b.idBiblioteca = :idBiblioteca")
    , @NamedQuery(name = "Biblioteca.findByPublica", query = "SELECT b FROM Biblioteca b WHERE b.publica = :publica")})
public class Biblioteca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBiblioteca")
    private Integer idBiblioteca;
    @Basic(optional = false)
    @Column(name = "publica")
    private short publica;
    @JoinColumn(name = "usuario_nombreUsuario", referencedColumnName = "nombreUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarionombreUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecaidBiblioteca")
    private List<Album> albumList;

    public Biblioteca() {
    }

    public Biblioteca(Integer idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public Biblioteca(Integer idBiblioteca, short publica) {
        this.idBiblioteca = idBiblioteca;
        this.publica = publica;
    }

    public Integer getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(Integer idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public short getPublica() {
        return publica;
    }

    public void setPublica(short publica) {
        this.publica = publica;
    }

    public Usuario getUsuarionombreUsuario() {
        return usuarionombreUsuario;
    }

    public void setUsuarionombreUsuario(Usuario usuarionombreUsuario) {
        this.usuarionombreUsuario = usuarionombreUsuario;
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
        hash += (idBiblioteca != null ? idBiblioteca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biblioteca)) {
            return false;
        }
        Biblioteca other = (Biblioteca) object;
        if ((this.idBiblioteca == null && other.idBiblioteca != null) || (this.idBiblioteca != null && !this.idBiblioteca.equals(other.idBiblioteca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Biblioteca[ idBiblioteca=" + idBiblioteca + " ]";
    }
    
}
