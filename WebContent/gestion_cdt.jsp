<%@page import="java.util.Hashtable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="partials/head_basico.html" %>
        <title>Gestion CDT</title>
        
    </head>
    <body class="gestion_cdt">
        
        <%@ include file="partials/menu.jsp" %>
        
        <% Hashtable<String, String> dat = (Hashtable<String, String>) request.getAttribute("datos"); %>
                
        <div class="contenido gestion_cdt">
            
            <h1>Gestión de CDT</h1>
            
            <div class="gestion_form">
                
                <form method="post" action="sgestionarcdt">
                    
                    <div class="col-6">
	                    <div class="gestion_crear">
	                        <label for="crear"><input id="crear" type="radio" name="gestion_cdt" value="crear" <%= dat.get("check_crear") %>>Crear un CDT</label>
	                        <label for="cuenta">Cuenta: <input id="cuenta" type="text" name="cuenta" value="<%= dat.get("text_cuenta") %>"></label>
	                        <label for="interes">Interés: <input id="interes" type="text" name="interes" value="<%= dat.get("text_interes") %>"></label>
	                        <label for="monto">Monto: <input id="monto" type="text" name="monto" value="<%= dat.get("text_monto") %>"></label>
	                    </div>
	                </div>
	                
                    <div class="col-6">
	                    <div class="gestion_cerrar">
	                        <label for="cerrar"><input id="cerrar" type="radio" name="gestion_cdt" value="cerrar" <%= dat.get("check_cerrar") %>>Cerrar un CDT</label>
	                        <label for="cdt">Nº. CDT: <input id="cdt" type="text" name="cdt" value="<%= dat.get("text_cdt") %>"></label>
	                    </div>
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
