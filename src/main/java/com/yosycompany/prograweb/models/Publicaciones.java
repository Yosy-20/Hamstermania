/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yosycompany.prograweb.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yosys
 */

@Entity
public class Publicaciones implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPublicacion;
    
    @ManyToOne
    private Usuarios usuario;
    private Categorias categoria;
    
    @Basic
    private String Titulo;
    private String Descripcion;
    private boolean estatus;
    
     @Temporal(TemporalType.TIMESTAMP)
    Date fechaCreacion;
    Date fechaMovimiento;

    public Publicaciones() {
    }

    public Publicaciones(int idPublicacion, Usuarios usuario, Categorias categoria, String Titulo, String Descripcion, boolean estatus, Date fechaCreacion, Date fechaMovimiento) {
        this.idPublicacion = idPublicacion;
        this.usuario = usuario;
        this.categoria = categoria;
        this.Titulo = Titulo;
        this.Descripcion = Descripcion;
        this.estatus = estatus;
        this.fechaCreacion = fechaCreacion;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPublicacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicaciones)) {
            return false;
        }
        Publicaciones other = (Publicaciones) object;
        if (this.idPublicacion != other.idPublicacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pw1.models.Publicaciones[ id=" + idPublicacion + " ]";
    }
    
    
}
