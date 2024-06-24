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
@WebServlet(name = "registro", urlPatterns = {"/registro"})
@MultipartConfig
public class SVregistro extends HttpServlet {

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
        String nombreUsuario=request.getParameter("username");
        String contraseña=request.getParameter("password");
        String confirmarContraseña = request.getParameter("confirmpass");
        
        try {
            // Verificamos si las contraseñas son iguales
            if (!contraseña.trim().equals(confirmarContraseña.trim())) {
                throw new Exception("Las contraseñas no coinciden");
            }
            if (!contraseña.matches(".*[A-Z].*")) {
                throw new Exception("Debe contener al menos una letra mayúscula");
            }
            if (!contraseña.matches(".*[a-z].*")) {
                throw new Exception("Debe contener al menos una letra minúscula");
            }
            
            if (!contraseña.matches(".*\\d.*")) {
                throw new Exception("Debe contener al menos un número");
            }
            
             if (!contraseña.matches(".*[!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",.<>/?].*")) {
                throw new Exception("Debe contener al menos un signo de puntuación");
            }
        } catch (Exception e) {
            
             request.setAttribute("passwordMismatchError", "Error: " + e.getMessage());
             request.getRequestDispatcher("/register.jsp").forward(request, response);
             return; 
        }
        
        
        
        
        
        String nombre=request.getParameter("names");
        String apellidos=request.getParameter("lastname");
        String correo=request.getParameter("email");
        
        try {
            // Verificamos si el correo cumple el formato
            if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                throw new Exception(" El correo no cumple con el formato");
            }
           
        } catch (Exception e) {
            
             request.setAttribute("emailFormatError", "Error: " + e.getMessage());
             request.getRequestDispatcher("/register.jsp").forward(request, response);
             return; 
        }
        
         
        
        String Sobremi=request.getParameter("sobremi");
        
        
        
        Part filePart=request.getPart("file");
        String fileName =filePart.getSubmittedFileName();
        String urlImg= nombreUsuario+""+fileName;
        
        filePart.write("C:\\Users\\yosys\\OneDrive\\Documentos\\NetBeansProjects\\PrograWeb\\src\\main\\webapp\\imagenes\\"+urlImg);
        
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
        UsuariosJpaController usuarioController= new UsuariosJpaController((EntityManagerFactory) Persistence.createEntityManagerFactory("bdPw"));
        Usuarios usuario = new Usuarios();
 
       Usuarios usuariorepetido= usuarioController.findUsuario3(nombreUsuario);
        
        try{
        if(usuariorepetido==null){
            
            
         usuario.setNombreUsuario(nombreUsuario);
       usuario.setPassword(contraseña);
       usuario.setNombres(nombre);
       usuario.setApellidos(apellidos);
       usuario.setCorreo(correo);
       usuario.setSobreMi(Sobremi);
       usuario.setUrlImagenPerfil(urlImg);
       usuario.setFechaNam(fecha_Nacimiento);
       usuario.setFechaCreate(fechaCreacion);
       usuario.setFechaMov(fechaMovimiento);
       usuario.setEstatus(true);
       
       usuarioController.create(usuario);
       
        request.getRequestDispatcher("/login.jsp").forward(request, response); 
        
        }else{
        
      throw new Exception("El usuario ya esta en uso");
            
        }
        }catch (Exception e) {
            
             request.setAttribute("usuariorepetido", "Error: " + e.getMessage());
             request.getRequestDispatcher("/register.jsp").forward(request, response);
             return; 
        }
//       try {
//        usuarioController.edit(usuario);
//        
//        
//       } catch (NonexistentEntityException e) {
//        // Manejo específico de la excepción NonexistentEntityException
//        System.err.println("Error: El usuario con el ID especificado no existe.");
//        e.printStackTrace();
//        } catch (Exception e) {
//        // Manejo de cualquier otra excepción
//        System.err.println("Error: Ocurrió un problema al actualizar el usuario.");
//        e.printStackTrace();
//        }
       
//       usuarioController.create(usuario);
//        
//       request.getRequestDispatcher("/login.jsp").forward(request, response); 
        
        
        
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
