<%-- 
    Document   : register
    Created on : 25 abr 2024, 18:06:27
    Author     : yosys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link rel="stylesheet" href="stylesregister.css">
    <link class="icon" rel="icon" type="image/x-icon" href="imagenes/Hamster.png">
</head>
<body>
    <div class="register-container">
        <form action="registro" method="post" enctype="multipart/form-data">
            <div class="register-form">
                <div class="left-column">
                    <h2>Registrar</h2>
                       
                    <div class="profile-pic">
                        <div class="preview">
                        <img for="file" id="image-preview" alt="Vista previa" name="img">
                        </div>
                        <input type="file" id="file" name="file" required>
                        <label for="file" class="upload-btn">Agregar Foto</label>
                        
                    </div>
                    <input type="text" placeholder="Usuario" name="username" required>
                    <% 
                        String usuariorepetido = (String)request.getAttribute("usuariorepetido");
                        if(usuariorepetido != null && !usuariorepetido.isEmpty()) {
                         %>
                         <p style="color: red;"><%= usuariorepetido %></p>
                         <% } %>
                    <input type="password" placeholder="Contraseña" name="password" required>
                    <input type="password" placeholder="Confirmar Contraseña" name="confirmpass" id="confirmpass" required>
                     <% 
                        String passwordMismatchError = (String)request.getAttribute("passwordMismatchError");
                        if(passwordMismatchError != null && !passwordMismatchError.isEmpty()) {
                         %>
                         <p style="color: red;"><%= passwordMismatchError %></p>
                         <% } %>
                </div>
                <div class="right-column">
                    
                    <h3>Información</h3>
                    
                    <div class="information">
                        <input type="text" class="name" placeholder="Nombre" name="names" required>
                        <input type="text" class="lastname" placeholder="Apellidos" name="lastname" required>
                        <input type="date" class="date" name="fechanam"required>
                        <input type="text" class="email" placeholder="Correo" name="email" required>
                        <% 
                        String emailFormatError = (String)request.getAttribute("emailFormatError");
                        if(emailFormatError != null && !emailFormatError.isEmpty()) {
                         %>
                         <p style="color: red;"><%= emailFormatError %></p>
                         <% } %>
                        
                        <textarea placeholder="Sobre mí" name="sobremi"></textarea>  
                        <input type="submit" value="Continuar">
                    </div>
                    <a href="login.jsp">Iniciar Sesion</a>
                </div>
            </div>
        </form>
        
    </div>
    <script src="js/script.js"></script>
</body>
</html>
