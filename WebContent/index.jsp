<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Inicio</title>
        
        <%@ include file="partials/head_basico.html" %>
        
    </head>
    <body class="index">
        
        <div class="contenido index" style="margin-top: 2em;">
        
            <h1>Aplicación Bancaria</h1>
            
            <div class="inicio_botones">
				<%@ include file="partials/acciones_banco.jsp" %>
            </div>
            
            <div style="text-align: center; margin: 1em">
            	<img src="imagenes/spinner.png">
            </div>
        
        </div>
    </body>
</html>