/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author raymundo
 */
public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String nombres;
    private String apellidos;
    private String correo;
    private List<Biblioteca> bibliotecaList;
    private List<Listareproduccion> listareproduccionList;

    public Usuario() {
    }

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Usuario(String nombreUsuario, String contrasena, String nombres, String apellidos, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Biblioteca> getBibliotecaList() {
        return bibliotecaList;
    }

    public void setBibliotecaList(List<Biblioteca> bibliotecaList) {
        this.bibliotecaList = bibliotecaList;
    }

    public List<Listareproduccion> getListareproduccionList() {
        return listareproduccionList;
    }

    public void setListareproduccionList(List<Listareproduccion> listareproduccionList) {
        this.listareproduccionList = listareproduccionList;
    }
}
