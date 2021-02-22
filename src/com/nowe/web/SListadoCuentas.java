package com.nowe.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nowe.modelo.CDT;
import com.nowe.modelo.Cuenta;
import com.nowe.persistencia.AccesoCuentasBancarias;



/**
 * Servlet implementation class SListadoCuentas
 */
@WebServlet("/slistadocuentas")
public class SListadoCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public AccesoCuentasBancarias db = new AccesoCuentasBancarias();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SListadoCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		Cookie[] cookies = request.getCookies();
		boolean permiso = request.getAttribute("permiso") != null;
		
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("cookie_test"))
				{
					permiso = true;
					break;
				}
			}
		}
		
		if(!permiso)
		{
			response.sendRedirect("acceso.jsp");
			return;
		}
		
		List<Map<String, String>> datos = null;
		List<CDT> cdts = null;
		
		try
		{
			//cuentas = db.obtenerCuentas();
			datos = db.obtenerListadoCuentas();
			cdts = db.obtenerCdts();
		}
		catch (ClassNotFoundException | SQLException ex)
		{
			System.out.println("Error SListadoCuentas doGet: " + ex.toString());
		}

		
		if(datos == null)
			datos = new ArrayList<Map<String, String>>();

		if(cdts == null)
			cdts = new ArrayList<CDT>();
			
		request.setAttribute("jspCuentas", datos);
		request.setAttribute("jspCdts", cdts);
		
		request.getRequestDispatcher("/listado_cuentas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cookie c = new Cookie("cookie_test", "");
		c.setMaxAge(10000);
		response.addCookie(c);
		
		request.setAttribute("permiso", true);
		
		doGet(request, response);
	}

}
