package com.nowe.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nowe.modelo.Movimiento;
import com.nowe.persistencia.AccesoCuentasBancarias;

/**
 * Servlet implementation class SIngresarRetirar
 */
@WebServlet("/singresarretirar")
@SuppressWarnings("unchecked")
public class SIngresarRetirar extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private Hashtable<String, String> datos;
	
	public AccesoCuentasBancarias db = new AccesoCuentasBancarias();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SIngresarRetirar() {
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
			datos.put("check_ingresar", "");
			datos.put("check_retirar", "");
			datos.put("text_cuenta", "");
			datos.put("text_monto", "");
			datos.put("res", "");
		}
		
		request.setAttribute("datos", datos);

		request.getRequestDispatcher("/ingresar_retirar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		datos = new Hashtable<String, String>();
		datos.put("check_ingresar", "");
		datos.put("check_retirar", "");
		datos.put("text_cuenta", "");
		datos.put("text_monto", "");
		
		String res = "";
		
        try 
        {
            String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
            
        	int cuenta = Integer.parseInt(request.getParameter("cuenta"));
            double monto = Double.parseDouble(request.getParameter("monto"));

            Movimiento mov = new Movimiento(monto, cuenta);

            switch (accion)
            {
            	case "ingresar":
            		if(db.Ingreso(mov))
            		{
            			res = "Éxito al realizar el ingreso";
            		}
            		else
            		{
            			datos.put("check_ingresar", "checked");
            			datos.put("text_cuenta", request.getParameter("cuenta"));
            			datos.put("text_monto", request.getParameter("monto"));
            			res = "Error interno: no se ha efectuado el ingreso";
            		}
            		break;
            		
            	case "retirar":
            		if(db.retiro(mov))
            		{
            			res = "Éxito al realizar la retirada";
            		}
            		else
            		{
            			datos.put("check_retirar", "checked");
            			datos.put("text_cuenta", request.getParameter("cuenta"));
            			datos.put("text_monto", request.getParameter("monto"));
            			res = "Error interno: no se ha efectuado la retirada";
            		}
            		break;
            	
            	default:
            		res = "Error: Debe elegir una acción";
        			datos.put("text_cuenta", request.getParameter("cuenta"));
        			datos.put("text_monto", request.getParameter("monto"));
            }
        }
        catch (ClassNotFoundException | SQLException ex)
        {
        	res = "Error en la BBDD, no se ha realizado la accion";
        }
        catch (NumberFormatException ex)
        {
        	res = "Error: Los datos deben ser numéricos";
        }
        
        datos.put("res", "<p>" + res + "</p>");
        
        request.setAttribute("datos", datos);
        request.getRequestDispatcher("/ingresar_retirar.jsp").forward(request, response);
	}

}
