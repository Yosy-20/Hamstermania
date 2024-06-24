<%-- 
    Document   : index
    Created on : 17 abr 2024, 01:01:31
    Author     : yosys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="styleslogin.css" rel="stylesheet">
    <link class="icon" rel="icon" type="image/x-icon" href="imagenes/Hamster.png">
</head>
<body>

<div class="login-container">
    <h2>Iniciar Sesión</h2>
    <form action="login" method="post">
    <input type="text" name="username" placeholder="Nombre de Usuario" required><br>
    <input type="password" name="password" placeholder="Contraseña" required><br>
    <input type="submit" value="Iniciar Sesión">
    </form>
    <a href="register.jsp">Regístrate aquí</a>
</div>
    
</body>
</html>
