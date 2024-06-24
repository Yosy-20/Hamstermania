/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yosycompany.prograweb.classes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author yosys
 */


public class Conexion {
    
    private static EntityManagerFactory emf = null;
    
    public static EntityManagerFactory CreateEnityManager(){
    
        if(emf==null)
          emf = Persistence.createEntityManagerFactory("bdPw");
        
    return emf;
    
    }
    
}

