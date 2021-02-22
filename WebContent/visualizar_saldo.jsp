
<%@page import="java.util.Hashtable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
    <head>
        
        <%@ include file="partials/head_basico.html" %>
        
        <title>Visualizar saldo</title>
        
    </head>
    <body class="visualizar_saldo">
        
        <%@ include file="partials/menu.jsp" %>
        
        <% Hashtable<String, String> dat = (Hashtable<String, String>) request.getAttribute("datos"); %>
                
        <div class="contenido visualizar_saldo">
            
            <h1>Consultar Saldo</h1>
            
            <div class="visualizar_form">
                
                <form method="post" action="svisualizarsaldo">
	                
	                <div class="col-6">
	                    <div class="visualizar_radios">
	                        <label for="radio_cuenta"><input id="radio_cuenta" type="radio" name="tipo_saldo" value="cuenta" <%= dat.get("check_cuenta") %>> Saldo cuenta</label>
	                        <label for="radio_cdt"><input id="radio_cdt" type="radio" name="tipo_saldo" value="cdt" <%= dat.get("check_cdt") %>> Saldo CDT</label>
	                        <label for="radio_cliente"><input id="radio_cliente" type="radio" name="tipo_saldo" value="cliente" <%= dat.get("check_cliente") %>> Saldo cliente</label>
	                    </div>
	                </div>
	                
	                <div class="col-6">
	                    <div class="visualizar_cuenta">
	                        <label for="text">Cuenta:</label><input id="text" type="text" name="cuenta">
	                    </div>
	                </div>
                
                    <div class="div_boton">
                        <input type="submit" value="Consultar">
                    </div>
                	
                	<div class="res_form"><%= dat.get("res") %></div>
                
                	<%@ include file="partials/final_form.html" %>
                                    
                </form>
                
            </div>
            
        </div>
        
    </body>
</html>
