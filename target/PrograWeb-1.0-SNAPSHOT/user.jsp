<%-- 
    Document   : user
    Created on : 27 abr 2024, 16:08:06
    Author     : yosys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yosycompany.prograweb.models.Publicaciones" %>
<%@ page import="com.yosycompany.prograweb.models.Usuarios" %>
<!DOCTYPE html>

<html lang="en">

<head>
  <!-- Metadatos -->
  <meta charset="utf-8">
  <meta name="author" content="Samanta Sanchez y Yaretzi Camacho ">
  <meta name="description" content="Desarrollo de ventanas Avance 1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="keywords" content="HTML, CSS, JavaScript, Jquery">
  <!-- Titulo -->
  <title>Hamstermania</title>
  <!-- Favicon -->
  <link class="icon" rel="icon" type="image/x-icon" href="imagenes/Hamster.png">
  <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  <!-- Iconos de Bootstrap -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
  <!-- CSS -->
  <link href="stylesuser.css" rel="stylesheet">
  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=Nunito+Sans:opsz,wght@6..12,200&family=Nunito:wght@500&display=swap"
    rel="stylesheet">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=Nunito+Sans:opsz,wght@6..12,200&family=Nunito:wght@500;900&display=swap"
    rel="stylesheet">
</head>

<body>
  <!-- Barra de navegación -->
  <nav class="navbar" >
    <div class="container-fluid">
      <!-- Usuario -->
      <li class="nav-logo">
          <a  href="home">
             <img src="imagenes/Hamster.png" alt="Logo"> 
          </a>
          <input id="Buscar" type="search"placeholder="  Buscar"  aria-label="Search">
      </li>
      <li class="nav-item">
          <a  href="login.jsp">
            <label  class="btn">Cerrar Sesion</label>
          </a>
     
        </button>
      </li>
          </form>
        </div>
        </div>
      </div>
  </nav>
<%      String idUsuario = String.valueOf(request.getSession().getAttribute("idUsuario"));
        String nombreUsuario = String.valueOf(request.getSession().getAttribute("nombreUsuario"));
        String urlImg = "imagenes/"+String.valueOf(request.getSession().getAttribute("urlImg"));
        String nombre = String.valueOf(request.getSession().getAttribute("nombre"));
        String apellidos = String.valueOf(request.getSession().getAttribute("apellido"));
        String email = String.valueOf(request.getSession().getAttribute("email"));
        String sobremi= String.valueOf(request.getSession().getAttribute("sobremi"));
          Date fechaNam = (Date) session.getAttribute("fecha");
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          String fechaNamFormateada = "";
        if (fechaNam != null) {
           fechaNamFormateada = sdf.format(fechaNam);
        }
        
        List<Publicaciones> listaPublicaciones =(List) request.getAttribute("ListaPublicaciones");
        %>

  <!-- Usuario Perfil  -->
  <section class="Usuario">
<div class="fondo-container">
    <div class="portada">

    </div>
    <div class="perfil">
        <img src=<%=urlImg%> alt="Foto de perfil">
    </div>
    
</div>
<div class="datos">
    <h3><%=nombreUsuario%></h3>
</div>
<div class="datos2">
    <label for="radio1"  class="upload-btn"> Publicaciones</label>
    <input type="radio" id="radio1" name="radios" onchange="mostrarDiv('div1')">
    <label for="radio2" class="upload-btn2"> Informacion</label>
    <input type="radio" id="radio2" name="radios" onchange="mostrarDiv('div2')">
  
    
</div>
<div  id="div1" class="Publicacion-muro">
 <% if(listaPublicaciones!=null)
            for(Publicaciones item : listaPublicaciones){   
            Usuarios usuarioPublicacion = item.getUsuario();
            String urlImage="imagenes/"+String.valueOf(usuarioPublicacion.getUrlImagenPerfil());%>
            
            <div class="Publicacion">
              <div class="Encabezado">
                <img src=<%=urlImage%> alt="Usuario"> 
                <h5><%=usuarioPublicacion.getNombreUsuario()%></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="Titulo">
                <h4><%=item.getTitulo()%></h4>
              </div>
              <div class="Descripcion" >
                <br>
                <p><%=item.getDescripcion()%></p>

              </div>
              


            </div>
                    <% }     %> 
      
      <div id="pagination" class="Pagination">
        <ul class="pagination">
            <li class="page-item" id="prev">
                <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <!-- Los números de página se generarán dinámicamente aquí -->
            <li class="page-item" id="next">
                <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
      </div>
</div>


<div  id="div2" class="Information-muro">
    <div class="user-container">     
                <div class="left-column">
                  <label for="radio4" class="upload-btn4">Información</label>
                  <input type="radio" id="radio4" name="radios" onchange="mostrarDiv2('divi1')">
                  <label for="radio5" class="upload-btn5">Editar</label>
                  <input type="radio" id="radio5" name="radios" onchange="mostrarDiv2('divi2')">
                  <label for="radio6" class="upload-btn5">Contraseña</label>
                  <input type="radio" id="radio6" name="radios" onchange="mostrarDiv2('divi3')">
                  
                  <form action="SVeliminar" method="post">
                      <input type="submit" value="Eliminar Cuenta" class="eliminarbtn">
                  </form>
                  
                </div>
                <div class="right-column">
                    <div class="information"  id="divi1">
                      <h5><%=nombre%></h5>
                      <h5><%=apellidos%></h5>
                      <br>
                      <h5> <%= fechaNamFormateada %></h5>
                      <h5><%=email%></h5>
                      <br>
                      <h5><%=sobremi%></h5>
                    </div>
                    <div class="editar" id="divi2">
                    <form action="SVeditar"  method="post" enctype="multipart/form-data">
                      <div class="left-column-editar">
                        <div class="profile-pic">
                          <div class="preview">
                          <img id="image-preview" src="#" alt="Vista previa de la imagen">
                          </div>
                          <input type="file" id="file" accept="image/*" name="files">
                          <label for="file" class="upload-btn">Agregar Foto</label>
                      </div>
                          <input type="text" placeholder="Usuario" name="nomusuario" required >
                         
                      </div>
                      <div class="right-column-editar">
                        <h5>Información</h5>
                        <div class="general">
                        <input type="text" class="name" placeholder="Nombre" name="nombres">
                        <input type="text" class="lastname" placeholder="Apellidos" name="apellido">
                        <input type="date" class="date" name="fechanam">
                        <input type="text" class="email" placeholder="Correo" name="correo">
                        <textarea placeholder="Sobre mí" name="sobremi"></textarea>  
                        <input type="submit" value="Continuar">
                        </div>
                      </div>
                    </form>
                    
                    </div>
                    <div class="contraseña" id="divi3">
                       <form action="contra" method="post">
                      <h5>Actualizar contraseña</h5>
                      <input type="text" placeholder="Contraseña actual" name="actual" required>
                      <% 
                        String passwordError = (String)request.getAttribute("passwordError");
                        if(passwordError != null && !passwordError.isEmpty()) {
                         %>
                         <p style="color: red;"><%= passwordError %></p>
                         <% } %>
                      <br>
                      <input type="text" placeholder="Nueva contraseña" name="nuvcontra" required>
                      <br>
                      <input type="text" placeholder="Confirmar contraseña" name="concontra" required>
                      <% 
                        String passwordMismatchError = (String)request.getAttribute("passwordMismatchError");
                        if(passwordMismatchError != null && !passwordMismatchError.isEmpty()) {
                         %>
                         <p style="color: red;"><%= passwordMismatchError %></p>
                         <% } %>
                      <br>
                      <input type="submit" value="Confirmar">
                    </form>
                    </div>

                </div>
            
        
    </div>
</div>

  </section>

  <footer class="seccion-oscura d-flex flex-column align-items-center justify-content-center"> 
    <img class="footer-logo" src="imagenes/Hamster.png" alt="Logo ">
    <p class="footer-texto text-center">Compartenos mas historias.<br>Por un mundo sin tragedias.</p>
    <div class="iconos-redes-sociales d-flex flex-wrap align-items-center justify-content-center">
      <a href="" target="_blank" rel="noopener noreferrer">
        <i class="bi bi-twitter"></i>
      </a>
      <a href="https://github.com/Yosy-20" target="_blank" rel="noopener noreferrer">
        <i class="bi bi-github"></i>
      </a>
      <a href="https://www.linkedin.com/in/samanta-sanchez20" target="_blank" rel="noopener noreferrer">
        <i class="bi bi-linkedin"></i>
      </a>
      <a href="https://www.instagram.com/yosegaytan/" target="_blank" rel="noopener noreferrer">
        <i class="bi bi-instagram"></i>
      </a>
      <a href="mailto:yosysam20@gmail.com" target="_blank" rel="noopener noreferrer">
        <i class="bi bi-envelope"></i>
      </a>
    </div>
    <div class="derechos-de-autor">Creado por Samanta Sanchez y Yaretzi Camacho(2024) &#169;</div>
  </footer>




<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="js/ejemplojquery.js"></script>
  <script src="js/bootstrap.bundle.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
</body>

</html>