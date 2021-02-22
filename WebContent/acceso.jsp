<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
        <%@ include file="partials/head_basico.html" %>
        
        <title>Acceso a listados</title>
        
    </head>
    <body class="acceso">
        
        <%@ include file="partials/menu.jsp" %>
        
        
                        
        <div class="contenido" style="text-align: center">
        
        	<h1>Estas a las puertas de Administración</h1>
        	
        	<div class="div_moria">
        		<img src="imagenes/moria.jpg">
        	</div>
        	
        	<h2 style="font-family: 'Akaya Telivigala', cursive;">Habla, amiga y entra</h2>
        	
        	<form method="post" action="slistadocuentas">
        	
        		<input type="text" name="pass" value="AMIGA" style="font-size:1.1em; text-align: center;">
        		
        		<input type="submit" value="Y entra" style="font-size:1.2em; font-family: 'Akaya Telivigala', cursive;">
        		
        	</form>
        	
        	<p style="text-align:left; padding-left: 3em;">
        		Al entrar, se instalará la cookie "cookie_test".<br>
        		Puede borrarla pulsando el botón "Salir" que aparecerá en el menu superior.
        	</p>
        	
        </div>

</body>
</html>