<ul>
	<li><a href="svisualizarsaldo">Visualizar saldo</a></li>
   	<li><a href="sgestionarcdt">Gestionar CDT</a></li>
   	<li><a href="singresarretirar">Ingresar / Retirar</a></li>
   	<li><a href="ssimulacion">Simulaci&oacute;n</a></li>
   	<li><a href="slistadocuentas">Listados</a></li>
	<% if(!request.getRequestURI().matches("/?[^/]+/index\\.jsp")){ %>
   		<li class="li_salir oculto" id="li_salir"><a href="ssalir">Salir</a></li>
	<% } %>
</ul>