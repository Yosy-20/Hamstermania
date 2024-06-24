<%-- 
    Document   : home
    Created on : 25 abr 2024, 19:44:30
    Author     : yosys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="com.yosycompany.prograweb.models.Categorias"%>
<%@page import="com.yosycompany.prograweb.models.Publicaciones"%>
<%@page import="com.yosycompany.prograweb.models.Usuarios"%>
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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>

  <!-- Iconos de Bootstrap -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
  <!-- CSS -->
  <link href="styles.css" rel="stylesheet">
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
 <%     String idUsuario = String.valueOf(request.getSession().getAttribute("idUsuario"));
        String nombreUsuario = String.valueOf(request.getSession().getAttribute("nombreUsuario"));
        String urlImg = "imagenes/"+String.valueOf(request.getSession().getAttribute("urlImg")); 
        List<Categorias> listaCategorias =(List) request.getAttribute("categoriasList");
        List<Categorias> listaCategorias2 =(List) request.getAttribute("categoriasList");
        List<Publicaciones> listaPublicaciones =(List) request.getAttribute("ListaPublicaciones");
        %>
<body>
  <!-- Barra de navegación -->
  <nav class="navbar">
    <div class="container-fluid">
      <!-- Usuario -->
      <ul class="navbar-nav">
        <li class="nav-logo">
          <a href="home">
            <img src="imagenes/Hamster.png" alt="Logo">
          </a>
          <form action="buscar">
            <input id="Buscar" type="search" placeholder="  Buscar" aria-label="Search" name="buscar">
          </form>
        </li>
      </ul>
      <ul class="navbar-nav1">
        <li class="nav-item">
          <img id="userPhoto" src="<%=urlImg%>" alt="Usuario" onclick="toggleDropdown()">
          <div id="dropdownMenu" class="dropdown-menu">
            <a href="user" class="dropdown-item"><%=nombreUsuario%></a>
            <a href="login.jsp" class="dropdown-item">Cerrar sesión</a>
          </div>
        </li>
      </ul>
    </div>
  </nav>

  <!-- Dashboard -->
  <section class="Dashboard">
    <div class="left-column">
      <!-- Filtro -->
      <h4>Filtrar</h4>
      <div class="Filtro-body">
          <form action="buscarA" method="post">
              <div class="Categoria">
          <h5>Categorías</h5>
        
              <label class="visually-hidden" for="autoSizingSelect">Preference</label>
          <select class="form-select" id="autoSizingSelect" name="cate" >
            <option value="todos" selected>Todos</option>
            <% if(listaCategorias != null) {
                int num = 1;
                for(Categorias item : listaCategorias) { %>
              <option value="<%=num%>"><%=item.getNombre()%></option>
              <% num = num + 1; } } %>
          </select>
        </div>
        <h5>Fecha</h5>
        <div class="date">
          <input type="date" class="date" name="date">
        </div>
        <input type="submit" value="Filtrar" class="filtrarbtn">
        
        
          </form>
        
        <img src="imagenes/Hamster.png" alt="Hamster">
      </div>
    </div>

    <div class="right-column">
      <!-- Publicacion -->
      <div class="Publicacion-body">
        <h3>Hamstermanía</h3>
        <div class="Muro-Publicacion">
          <div class="Publicacion-crear">
            <img src="<%=urlImg%>" alt="Usuario">
            <button type="button" class="boton" data-bs-toggle="modal" data-bs-target="#exampleModal">Comparte tu Historia</button>
            <img src="imagenes/Hamster.png" alt="publicar">
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog modal-dialog-centered modal-lg">
                <form action="Publicar" method="post">
                  <div class="modal-content">
                    <div class="modal-header">
                      <img src="<%=urlImg%>" alt="Usuario">
                      <h5><%=nombreUsuario%></h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <div class="title">
                        <input type="text" name="titulo" placeholder="Titulo" required><br>
                      </div>
                      <div class="description">
                        <br>
                        <textarea placeholder="Descripcion" name="descripcion" required></textarea>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <label class="visually-hidden" for="autoSizingSelect">Preference</label>
                      <select class="form-select" id="autoSizingSelect" name="Categoria" required>
                        <option selected>Categoria</option>
                        <% if(listaCategorias != null) {
                            int num1 = 1;
                            for(Categorias item : listaCategorias) { %>
                          <option value="<%=item.getNombre()%>"><%=item.getNombre()%></option>
                          <% num1 = num1 + 1; } } %>
                      </select>
                      <button type="submit" class="btn-editar">Publicar</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <% if(listaPublicaciones != null) {
              for(Publicaciones item : listaPublicaciones) {
                  Usuarios usuarioPublicacion = item.getUsuario();
                  
                  String urlImage = "imagenes/" + String.valueOf(usuarioPublicacion.getUrlImagenPerfil()); %>
            <div class="Publicacion" data-id="<%=item.getIdPublicacion()%>">
              
            <div class="Encabezado">
               <img src="<%=urlImage%>" alt="Usuario">
               <h5><%=usuarioPublicacion.getNombreUsuario()%></h5>
                <button type="button"  class="btn-dots" id="btnPubli" aria-expanded="false" onclick="toggleDropdown2()"" > </button>
               <div class="dropdown-menu2 dropdown-menu2-end" id="dropdownMenu2" aria-labelledby="btnPubli" >
               <a href="" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#exampleModal2">Editar</a>
               <a href="" class="dropdown-item">Eliminar</a>
               </div>
               <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg">
                  <div class="modal-content">
                    <div class="modal-header">
                      <img src="<%=urlImg%>" alt="Usuario">
                      <h5><%=nombreUsuario%></h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                      <form action="editarP">
                        <div class="modal-body">
                        <div class="title">
                       
                          <input type="hidden" name="publicacionId" value="<%=item.getIdPublicacion()%>">
                          <input type="text" name="tituloe" placeholder="Titulo" required><br>
                        </div>
                        <div class="description" 
                          <br>
                          <textarea placeholder="Descripcion" name="descripcione"></textarea> 
                        </div>
                      
                          </div>
                     <div class="modal-footer">
                      <label class="visually-hidden" for="autoSizingSelect">Preference</label>
                      <select class="form-select" id="autoSizingSelect" name="Categoriae" required>
                        <option selected>Categoria</option>
                        <% if(listaCategorias2 != null) {
                            int num1 = 1;
                            for(Categorias item2 : listaCategorias2) { %>
                          <option value="<%=item2.getNombre()%>"><%=item2.getNombre()%></option>
                          <% num1 = num1 + 1; } } %>
                      </select>
                      <button type="submit" class="btn-editar">Editar</button>
                      
                     </div>
                    </form>
                   
                  </div>
                </div>
              </div>
               
               
            </div>
            <div class="Titulo">
               <h4><%=item.getTitulo()%></h4>
            </div>
            <div class="Descripcion">
               <br>
               <p><%=item.getDescripcion()%></p>
            </div>
            </div>
          <% } } %>
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
      </div>
    </div>
  </section>

  <footer class="seccion-oscura d-flex flex-column align-items-center justify-content-center">
    <img class="footer-logo" src="imagenes/Hamster.png" alt="Logo">
    <p class="footer-texto text-center">Compártenos más historias.<br>Por un mundo sin tragedias.</p>
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
    <div class="derechos-de-autor">Creado por Samanta Sanchez y Yaretzi Camacho (2024) &#169;</div>
  </footer>

  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="js/ejemplojquery.js"></script>
  <script src="js/bootstrap.bundle.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
  
</body>




</html>