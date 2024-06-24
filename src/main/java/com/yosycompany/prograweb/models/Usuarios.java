/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yosycompany.prograweb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
/**
 *
 * @author yosys
 */
@Entity
public class Usuarios implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int idUsuario;
    
  
  @Basic
  private String nombreUsuario;
    private String nombres;
    private String password;
    private String apellidos;
    private String urlImagenPerfil;
    private String correo;
    private String sobreMi;
    private boolean estatus;
    
    @Temporal(TemporalType.DATE)
    Date fechaNam;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date fechaCreate;
    Date fechaMov;

    public Usuarios() {
    }

    public Usuarios(int idUsuario, String nombreUsuario, String nombres, String password, String apellidos, String urlImagenPerfil, String correo, String sobreMi, boolean estatus, Date fechaNam, Date fechaCreate, Date fechaMov) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombres = nombres;
        this.password = password;
        this.apellidos = apellidos;
        this.urlImagenPerfil = urlImagenPerfil;
        this.correo = correo;
        this.sobreMi = sobreMi;
        this.estatus = estatus;
        this.fechaNam = fechaNam;
        this.fechaCreate = fechaCreate;
        this.fechaMov = fechaMov;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUrlImagenPerfil() {
        return urlImagenPerfil;
    }

    public void setUrlImagenPerfil(String urlImagenPerfil) {
        this.urlImagenPerfil = urlImagenPerfil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Date getFechaNam() {
        return fechaNam;
    }

    public void setFechaNam(Date fechaNam) {
        this.fechaNam = fechaNam;
    }

    public Date getFechaCreate() {
        return fechaCreate;
    }

    public void setFechaCreate(Date fechaCreate) {
        this.fechaCreate = fechaCreate;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
    }
    
    
    
    
}
