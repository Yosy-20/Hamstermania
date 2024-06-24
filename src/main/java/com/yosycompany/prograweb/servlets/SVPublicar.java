/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yosycompany.prograweb.servlets;

import com.yosycompany.prograweb.models.Categorias;
import com.yosycompany.prograweb.models.Publicaciones;
import com.yosycompany.prograweb.models.Usuarios;
import com.yosycompany.prograweb.persistencia1.CategoriasJpaController;
import com.yosycompany.prograweb.persistencia1.PublicacionesJpaController;
import com.yosycompany.prograweb.persistencia1.UsuariosJpaController;
import java.io.IOException;
//import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yosys
 */
@WebServlet(name = "Publicar", urlPatterns = {"/Publicar"})
public class SVPublicar extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         int idUsuario = (int) request.getSession().getAttribute("idUsuario");
         String titulo = request.getParameter("titulo");
         String descripcion = request.getParameter("descripcion");
         String nombreCategoria= request.getParameter("Categoria");
         Date fechaCreacion = new Date();
         Date fechaMovimiento = new Date();
         
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdPw");
         
         UsuariosJpaController usuarioController =  new UsuariosJpaController(emf);
         Usuarios usuario = usuarioController.findUsuarios(idUsuario);
         
         CategoriasJpaController categoriaController =  new CategoriasJpaController(emf);
         Categorias categoria = categoriaController.findCategorias(nombreCategoria);
         
         
         PublicacionesJpaController publicacionController =  new PublicacionesJpaController(emf);
         Publicaciones publicacion = new Publicaciones();
         
         publicacion.setTitulo(titulo);
         publicacion.setDescripcion(descripcion);
         publicacion.setCategoria(categoria);
         publicacion.setFechaCreacion(fechaCreacion);
         publicacion.setFechaMovimiento(fechaMovimiento);
         publicacion.setEstatus(true);
         publicacion.setUsuario(usuario);
         
         publicacionController.create(publicacion);
         
          request.getRequestDispatcher("home").forward(request, response);
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
