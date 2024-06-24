/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yosycompany.prograweb.servlets;

import com.yosycompany.prograweb.models.Usuarios;
import com.yosycompany.prograweb.persistencia1.UsuariosJpaController;
import com.yosycompany.prograweb.persistencia1.exceptions.NonexistentEntityException;
import javax.persistence.Persistence;
import java.io.IOException;
//import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
/**
 *
 * @author yosys
 */
@WebServlet(name = "SVeditar", urlPatterns = {"/SVeditar"})
@MultipartConfig
public class SVeditar extends HttpServlet {

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
       
                    String nombreUsuario = request.getParameter("nomusuario");
                    //String contraseña = request.getParameter("contra");//hacer que me guarde la contra que ya tiene el usuario
                    String nombre = request.getParameter("nombres");
                    String apellidos = request.getParameter("apellido");
                    String correo = request.getParameter("correo");
                    String Sobremi = request.getParameter("sobremi");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String text_fechaNacimiento = request.getParameter("fechanam");
                    Date fecha_Nacimiento = null;
                    
                    try {
                    fecha_Nacimiento = dateFormat.parse(text_fechaNacimiento);
                          }catch(Exception e) {
                              System.out.println("Error occurred"+ e.getMessage());
                                return;
                         }

                     Date fechaCreacion = new Date();
                     Date fechaMovimiento = new Date();
 
                   
                    Part filePart=request.getPart("files");
                    String fileName =filePart.getSubmittedFileName();
                    String urlImg= nombreUsuario+""+fileName;
                    filePart.write("C:\\Users\\yosys\\OneDrive\\Documentos\\NetBeansProjects\\PrograWeb\\src\\main\\webapp\\imagenes\\"+urlImg);
 
       //validaciones
       
       
                    
                    
                    
                    
                    
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdPw");
         
         UsuariosJpaController usuarioController =  new UsuariosJpaController(emf);
                Usuarios usuario = usuarioController.findUsuarios(idUsuario);
                
                  
                
                  
                    usuario.setNombreUsuario(nombreUsuario);
                    //usuario.setPassword(contraseña);
                    usuario.setNombres(nombre);
                    usuario.setApellidos(apellidos);
                    usuario.setCorreo(correo);
                    usuario.setSobreMi(Sobremi);
                    usuario.setUrlImagenPerfil(urlImg);
                    usuario.setFechaNam(fecha_Nacimiento);
                    usuario.setFechaCreate(fechaCreacion);
                    usuario.setFechaMov(fechaMovimiento);
                    //usuario.setEstatus(true);
                    
                    
 
            try {
        usuarioController.edit(usuario);
        request.getRequestDispatcher("user").forward(request, response);

            
        //response.sendRedirect("user.jsp");
       // request.getRequestDispatcher("SVhome").forward(request, response);

       } catch (Exception e) {
           // Manejo específico de la excepción NonexistentEntityException
          System.err.println("Error: no edito.");
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
