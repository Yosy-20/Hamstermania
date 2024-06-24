/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yosycompany.prograweb.servlets;

import com.yosycompany.prograweb.models.Publicaciones;
import com.yosycompany.prograweb.models.Usuarios;
import com.yosycompany.prograweb.persistencia1.PublicacionesJpaController;
import com.yosycompany.prograweb.persistencia1.UsuariosJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yosys
 */
@WebServlet(name = "user", urlPatterns = {"/user"})
@MultipartConfig
public class SVuser extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdPw");
        
        PublicacionesJpaController PublicacionesController =  new PublicacionesJpaController(emf);
         String idUsuarioStr = String.valueOf(request.getSession().getAttribute("idUsuario"));
        if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
            
                int idUsuario = Integer.parseInt(idUsuarioStr);
                List<Publicaciones> listaPublicaciones = PublicacionesController.findPublicacionesEntities(idUsuario);

                request.setAttribute("ListaPublicaciones", listaPublicaciones);
//                request.getRequestDispatcher("/user.jsp").forward(request, response);
            
        } 
        int idUsuario = (int) request.getSession().getAttribute("idUsuario");
                UsuariosJpaController usuarioController= new UsuariosJpaController((EntityManagerFactory) Persistence.createEntityManagerFactory("bdPw"));
                Usuarios usuario = usuarioController.findUsuarios(idUsuario);
        if(usuario!=null){
            request.getSession().setAttribute("idUsuario", usuario.getIdUsuario());
            request.getSession().setAttribute("nombreUsuario", usuario.getNombreUsuario());
            request.getSession().setAttribute("email", usuario.getCorreo());
            request.getSession().setAttribute("nombre", usuario.getNombres());
            request.getSession().setAttribute("apellido", usuario.getApellidos());
            request.getSession().setAttribute("fecha", usuario.getFechaNam());
            request.getSession().setAttribute("sobremi", usuario.getSobreMi());
             HttpSession session = request.getSession();
              session.setAttribute("urlImg", usuario.getUrlImagenPerfil());
            request.getRequestDispatcher("/user.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/user.jsp").forward(request, response); 
             System.out.println("<h1>Error:Usuario o Contraseña incorrecta</h1>");
        }
                //request.getRequestDispatcher("/user.jsp").forward(request, response);
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
