<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
	<head>
        <%@ include file="partials/head_basico.html" %>
        
        <title>Simulación</title>
        
    </head>
    <body class="simulacion">
        
        <%@ include file="partials/menu.jsp" %>
        
        <% Hashtable<String, String> dat = (Hashtable<String, String>) request.getAttribute("datos"); %>
                        
        <div class="contenido simulacion">
            
            <h1>Simulación</h1>
            
            <div class="simulacion_form">
                
                <form method="post" action="ssimulacion">
	                
	                <div class="simulacion_text">
	                    <label for="text">NIF: </label><input id="text" type="text" name="nif">
	                </div>
                
                    <div class="div_boton">
                        <input type="submit" value="Ejecutar simulación de un mes">
                    </div>
                	
                	<div class="res_form"><%= dat.get("res") %></div>
                
                	<%@ include file="partials/final_form.html" %>
                                    
                </form>
                
            </div>
            
        </div>
        
    </body>
</html>
