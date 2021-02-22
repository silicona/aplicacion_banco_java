
<%@page import="java.util.Hashtable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="partials/head_basico.html" %>
        <title>Ingresar / Retirar</title>
        
    </head>
    <body class="ingresar_retirar">
        
        <%@ include file="partials/menu.jsp" %>
        
        <% Hashtable<String, String> dat = (Hashtable<String, String>) request.getAttribute("datos"); %>
        
        <div class="contenido ingresar_retirar">
            
            <h1>Ingresar - Retirar</h1>
            
            <div class="ingresar_form">
                
                <form method="post" action="singresarretirar">
                    
                    <div class="ingresar_radios">
                        <label for="ingresar"><input id="ingresar" type="radio" name="accion" value="ingresar" <%= dat.get("check_ingresar") %>>Ingresar</label>
                        <label for="retirar"><input id="retirar" type="radio" name="accion" value="retirar" <%= dat.get("check_retirar") %>>Retirar</label>
                    </div>
                    
                    <div class="ingresar_text">
                        <label for="cuenta">Cuenta: <input id="cuenta" type="text" name="cuenta" value="<%= dat.get("text_cuenta") %>"></label>
                        <label for="monto">Monto: <input id="monto" type="text" name="monto" value="<%= dat.get("text_monto") %>"></label>
                    </div>
                    
                    <div class="div_boton">
                        <input type="submit" value="Realizar acción">
                    </div>
                	
                	<div class="res_form"><%= dat.get("res") %></div>
                
                	<%@ include file="partials/final_form.html" %>
                                    
                </form>

            </div>
            
        </div>
        
    </body>
</html>
