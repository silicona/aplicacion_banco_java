package com.nowe.web;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nowe.persistencia.AccesoCuentasBancarias;

/**
 * Servlet implementation class SVisualizarSaldo
 */
@WebServlet("/svisualizarsaldo")
@SuppressWarnings("unchecked")
public class SVisualizarSaldo extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private Hashtable<String, String> datos;
	
	private AccesoCuentasBancarias db = new AccesoCuentasBancarias();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SVisualizarSaldo() {
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
			datos.put("check_cuenta", "");
			datos.put("check_cdt", "");
			datos.put("check_cliente", "");
			datos.put("res", "");
		}
		
		request.setAttribute("datos", datos);
		
		request.getRequestDispatcher("/visualizar_saldo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		datos = new Hashtable<String, String>();
		datos.put("check_cuenta", "");
		datos.put("check_cdt", "");
		datos.put("check_cliente", "");
		
		String res = "";
		String tipo = request.getParameter("tipo_saldo") != null ? request.getParameter("tipo_saldo") : "";
		String cuenta = request.getParameter("cuenta");
		
		switch(tipo)
		{
			case "cliente":
				datos.put("check_cliente", "checked");
				
				if(cuenta.matches("\\d{8}\\w"))
				{
					try
					{	
						res = "El saldo del cliente " + cuenta + " es: " + Double.toString(db.consultaSaldoTotal(cuenta));
					}
					catch (Exception e)
					{
						res = "Error: " + e.toString();
					}
				}
				else
				{
					res = "Error: El NIF no tiene formato válido.";
				}
				break;
			case "cdt":
				datos.put("check_cdt", "checked");
				try
				{
					int id = Integer.parseInt(cuenta);
					
					res = "El saldo del CDT " + id + " es: " + Double.toString(db.consultaSaldoCDT(id));
					
				}
				catch (Exception e)
				{
					res = "Debe introducir un número entero para esta operación";
				}
				break;
			case "cuenta":
				datos.put("check_cuenta", "checked");
				try
				{
					int id = Integer.parseInt(cuenta);

					res = "El saldo de la cuenta " + id + " es: " + Double.toString(db.consultaSaldo(id));
				}
				catch (Exception e)
				{
					if(e.getClass().getName().equals("com.microsoft.sqlserver.jdbc.SQLServerException"))
					{
						res = "Error interno: Error al contactar con Base de datos";
					}
					else
					{
						res = "Debe introducir un número entero para esta operación";
					}
				}
				break;
			default:
				res = "Error: no se ha especificado el tipo de saldo";			
		}
		
		datos.put("res", "<p>" + res + "</p>");
		
		request.setAttribute("datos", datos);
		request.getRequestDispatcher("/visualizar_saldo.jsp").forward(request, response);
	}
}
