package com.nowe.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nowe.persistencia.AccesoCuentasBancarias;

/**
 * Servlet implementation class SSimulacion
 */
@WebServlet("/ssimulacion")
@SuppressWarnings("unchecked")
public class SSimulacion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private Hashtable<String, String> datos;
	
	public AccesoCuentasBancarias db = new AccesoCuentasBancarias();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSimulacion() {
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
			datos.put("text_nif", "");
			datos.put("res", "");
		}
		
		request.setAttribute("datos", datos);

		request.getRequestDispatcher("/simulacion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        String res = "";
        
		try
        {
            String nif = request.getParameter("nif");
            
            if(!nif.matches("\\d{8}\\w")) {
            	throw new NumberFormatException();
            }

            res = String.format("Su saldo simulado es %.2f", db.simulacion(nif));
        }
        catch (ClassNotFoundException | SQLException ex)
        {
        	res = "Error interno, la simulación no se ha podido realizar";
        }
        catch (NumberFormatException ex)
        {
        	res = "Error: el NIF no tiene el formato correcto";
        }
        
        datos.put("res", "<p>" + res + "</p>");
        
        request.setAttribute("datos", datos);
        
        request.getRequestDispatcher("simulacion.jsp").forward(request, response);
	}

}
