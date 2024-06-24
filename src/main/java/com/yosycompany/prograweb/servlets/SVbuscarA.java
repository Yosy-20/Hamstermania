/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yosycompany.prograweb.servlets;

import com.yosycompany.prograweb.models.Categorias;
import com.yosycompany.prograweb.models.Publicaciones;
import com.yosycompany.prograweb.persistencia1.CategoriasJpaController;
import com.yosycompany.prograweb.persistencia1.PublicacionesJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yosys
 */
@WebServlet(name = "buscarA", urlPatterns = {"/buscarA"})
public class SVbuscarA extends HttpServlet {

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
        
         
    String categoriaIdStr = request.getParameter("cate");
    String fechaStr = request.getParameter("date");
    Date fechaCreacion = null;

    if (fechaStr != null && !fechaStr.isEmpty()) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaCreacion = dateFormat.parse(fechaStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdPw");
    PublicacionesJpaController publicacionesController = new PublicacionesJpaController(emf);
     Integer categoriaId = null;
    if (categoriaIdStr != null && !categoriaIdStr.equals("todos")) {
        try {
            categoriaId = Integer.parseInt(categoriaIdStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    List<Publicaciones> listaPublicaciones = publicacionesController.findPublicacionesEntities(categoriaId, fechaCreacion);
    request.setAttribute("ListaPublicaciones", listaPublicaciones);
    
    CategoriasJpaController CategoriaController =  new CategoriasJpaController(emf);
         
         List<Categorias> categoriasList = CategoriaController.findCategoriasEntities();
         request.setAttribute("categoriasList", categoriasList);
    
    request.getRequestDispatcher("/home.jsp").forward(request, response);
        
        
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
