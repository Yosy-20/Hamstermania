/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yosycompany.prograweb.servlets;

import com.yosycompany.prograweb.models.Usuarios;
import com.yosycompany.prograweb.persistencia1.UsuariosJpaController;
//import com.yosycompany.prograweb.persistencia1.exceptions.NonexistentEntityException;
import javax.persistence.Persistence;
import java.io.IOException;
//import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yarel
 */
@WebServlet(name = "contra", urlPatterns = {"/contra"})
public class SVeditcontra extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 
         // Obtener el ID del usuario desde la sesión
       int idUsuario = (int) request.getSession().getAttribute("idUsuario");
       
       String actcontraseña = request.getParameter("actual");
       
       String contraseñas = request.getParameter("nuvcontra");
        String concontraseña = request.getParameter("concontra");
         
       
               try {
            // Verificamos si las contraseñas son iguales
            if (!contraseñas.trim().equals(concontraseña.trim())) {
                throw new Exception("Las contraseñas no coinciden");
            }
 
            if (!contraseñas.matches(".*[A-Z].*")) {
                throw new Exception("Debe contener al menos una letra mayúscula");
            }
            if (!contraseñas.matches(".*[a-z].*")) {
                throw new Exception("Debe contener al menos una letra minúscula");
            }
            
            if (!contraseñas.matches(".*\\d.*")) {
                throw new Exception("Debe contener al menos un número");
            }
            
             if (!contraseñas.matches(".*[!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",.<>/?].*")) {
                throw new Exception("Debe contener al menos un signo de puntuación");
            }
        } catch (Exception e) {
            
             request.setAttribute("passwordMismatchError", "Error: " + e.getMessage());
             request.getRequestDispatcher("/user.jsp").forward(request, response);
             return; 
        }
       
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdPw");
         
         UsuariosJpaController usuarioController =  new UsuariosJpaController(emf);
         
                Usuarios usuario1= usuarioController.findUsuarioByPasswordAndId(actcontraseña,idUsuario);
              
                 
                 try{
                 if (usuario1!=null){
                 
                  Usuarios usuario = usuarioController.findUsuarios(idUsuario);
                  usuario.setPassword(contraseñas);
                
                  usuarioController.edit(usuario);
                request.getRequestDispatcher("/user.jsp").forward(request, response);
                 }else{throw new Exception("contraseña incorrecta");}
                 }catch(Exception e){
                 request.setAttribute("passwordError", "Error: " + e.getMessage());
                 }
                 

         
    }


    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
