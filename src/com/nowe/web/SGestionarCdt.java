package com.nowe.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nowe.modelo.CDT;
import com.nowe.persistencia.AccesoCuentasBancarias;

/**
 * Servlet implementation class SGestionarCdt
 */
@WebServlet("/sgestionarcdt")
@SuppressWarnings("unchecked")
public class SGestionarCdt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Hashtable<String, String> datos;
	
	public AccesoCuentasBancarias db = new AccesoCuentasBancarias();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SGestionarCdt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		datos = (Hashtable<String, String>) request.getAttribute("datos");
		
		if( datos == null )
		{
			datos = new Hashtable<String, String>();
			datos.put("check_crear", "");
			datos.put("check_cerrar", "");
			datos.put("text_cuenta", "");
			datos.put("text_interes", "");
			datos.put("text_monto", "");
			datos.put("text_cdt", "");
			datos.put("res", "");
		}
		
		request.setAttribute("datos", datos);
		
		
		request.getRequestDispatcher("/gestion_cdt.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		datos = new Hashtable<String, String>();
		datos.put("check_crear", "");
		datos.put("check_cerrar", "");
		datos.put("text_cuenta", "");
		datos.put("text_interes", "");
		datos.put("text_monto", "");
		datos.put("text_cdt", "");
		
		String res = "";
		
		String gestion = request.getParameter("gestion_cdt") != null ? request.getParameter("gestion_cdt") : "";
		
		switch(gestion)
		{
			case "crear":
	            try {
	                int cuenta = Integer.parseInt(request.getParameter("cuenta"));
	                double interes = Double.parseDouble(request.getParameter("interes"));
	                double monto = Double.parseDouble(request.getParameter("monto"));

	                //1.2 Instanciar un objeto CDT
	                CDT inversion = new CDT(cuenta, interes, monto);

	                //1.3 Llamar a creación del CDT
	                //exito = acb1.crearInversion(inversion);
	                
	                if (db.crearInversion(inversion))
	                {
	                	res = "Se ha realizado el alta de su CDT correctamente";
	                }
	                else 
	                {
	                	res = "Se ha producido un error, no se realizó la acción correctamente";
		            	datos.put("check_crear", "checked");
		        		datos.put("text_cuenta", request.getParameter("cuenta"));
		        		datos.put("text_interes", request.getParameter("interes"));
		        		datos.put("text_monto", request.getParameter("monto"));
	                }

	            }
	            catch (ClassNotFoundException | SQLException ex)
	            {
	            	res = "Error interno: no se realizó la acción correctamente";
	            	datos.put("check_crear", "checked");
	        		datos.put("text_cuenta", request.getParameter("cuenta"));
	        		datos.put("text_interes", request.getParameter("interes"));
	        		datos.put("text_monto", request.getParameter("monto"));
	            	
	            }
	            catch (NumberFormatException ex) {
	            	res = "Error: Los datos deben ser numéricos";
	            	datos.put("check_crear", "checked");
	        		datos.put("text_cuenta", request.getParameter("cuenta"));
	        		datos.put("text_interes", request.getParameter("interes"));
	        		datos.put("text_monto", request.getParameter("monto"));
	            }
	            break;
			case "cerrar":
				try
				{
	                int cdt = Integer.parseInt(request.getParameter("cdt"));

	                if (db.cerrarInversion(cdt))
	                {
	                	res = "Se ha realizado el cierre de su CDT correctamente";
	                }
	                else
	                {
	                	res = "Se ha producido un error, no se realizó la acción correctamente";
	                	datos.put("check_cerrar", "checked");
	                	datos.put("text_cdt", request.getParameter("cdt"));
	                }
	                
	            }
				catch (ClassNotFoundException | SQLException ex)
				{
					res = "Error interno: no se realizó la acción correctamente";
					datos.put("check_cerrar", "checked");
					datos.put("text_cdt", request.getParameter("cdt"));
	            }
				catch (NumberFormatException ex)
				{
					res = "Error: El número del CDT debe ser numérico";
					datos.put("check_cerrar", "checked");
	            }
				break;
			default:
				res = "Debe elegir una acción y rellenar los datos correspondientes.";
		}
		
		datos.put("res", "<p>" + res + "</p>");
		
		request.setAttribute("datos", datos);
		
		request.getRequestDispatcher("gestion_cdt.jsp").forward(request, response);			
	}

}
