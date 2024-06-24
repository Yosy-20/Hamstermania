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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yosys
 */

@Entity
public class Categorias implements Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCategoria;
     
     @Basic
     private String Nombre;
     
     @Temporal(TemporalType.TIMESTAMP)
    Date fechaCreacion;
    Date fechaMovimiento;

    public Categorias() {
    }

    public Categorias(int idCategoria, String Nombre, Date fechaCreacion, Date fechaMovimiento) {
        this.idCategoria = idCategoria;
        this.Nombre = Nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
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
    
    
     
     
    
}
