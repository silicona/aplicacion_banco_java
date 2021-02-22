<%@page import="java.util.Map"%>
<%@page import="com.nowe.modelo.CDT"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.nowe.modelo.Cuenta" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
    <head>
    
        <%@ include file="partials/head_basico.html" %>
        
        <title>Listado de cuentas</title>
        
    </head>
    <body class="listados">
        
        <%@ include file="partials/menu.jsp" %>    
        
        <% 
        	//List<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("jspCuentas");
        	List<Map<String, String>> cuentas = (ArrayList<Map<String, String>>) request.getAttribute("jspCuentas");
        	List<CDT> cdts = (ArrayList<CDT>) request.getAttribute("jspCdts"); 
        %>
        
        <div class="contenido">
        
        	<h1>Listados</h1>
        	
        	<p class="boton_recarga"><a href="slistadocuentas">Actualizar</a><p>
        	
        	<div class="div_listados">
        	
        		<h3>Cuentas</h3>

		        <table class="cuentas">
		        	<tr>
		        		<th class="id">Id</th>
		        		<th>Saldo</th>
		        		<th>NIF</th>
		        		<th>Nombre</th>
		        		<th>Estado</th>
		        	</tr>
		        	
		        	<% for(Map<String, String> h : cuentas) { %>
		        		<% String estado = h.get("estado").equalsIgnoreCase("A") ? "Abierta" : "Cerrada"; %>
		        		<tr>
		        			<td class="id"><%= h.get("id") %></td>
		        			<td class="der"><%= h.get("saldo") %></td>
		        			<td><%= h.get("nif") %></td>
		        			<td><%= h.get("nombre") %>
		        			<td><%= estado %></td>
		        		</tr>
		        		
		        	<% } %>
		        	
		        </table>
		        
		        <h3>CDTs</h3>
		              
		        <table class="cdts">
		        	<tr>
		        		<th class="id">Id</th>
		        		<th class="id">Id&nbsp;cuenta</th>
		        		<th>Apertura</th>
		        		<th>Intereses</th>
		        		<th>Valor</th>
		        		<th>Estado</th>
		        	</tr>
		        	
		        	<% for(CDT c : cdts) { %>
		        		<% String estado = c.getEstado().equalsIgnoreCase("A") ? "Abierto" : "Cerrado"; %>
		        		<tr>
		        			<td class="id"><%= c.getIdInversion() %></td>
		        			<td class="id"><%= c.getIdCuenta() %></td>
		        			<td class=""><%= c.getFechaApertura() %></td>
		        			<td class="id"><%= c.getInteresesMensuales() %></td>
		        			<td class="der"><%= c.getValorInversion() %></td>
		        			<td><%= estado %></td>
		        		</tr>
		        		
		        	<% } %>
		        	
		        </table>  
		    </div>
	        
	   </div>

</body>
</html>