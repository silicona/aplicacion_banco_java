/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nowe.persistencia;

import com.nowe.modelo.CDT;
import com.nowe.modelo.Movimiento;
import com.nowe.modelo.Cuenta;
import com.nowe.modelo.EstadoCuenta;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Programacion
 */
public class AccesoCuentasBancarias extends Conexion
{
    public double consultaSaldo(int idCuenta) throws ClassNotFoundException, SQLException
    {
        CallableStatement cmd;
        ResultSet rs;

        double saldo=0;
        String sql = "EXEC CONSULTA_CC ?";
        
        abrirConexion();
        
        cmd = miConexion.prepareCall(sql);
        
        cmd.setInt(1, idCuenta);
        
        rs = cmd.executeQuery();
        
        if (rs.next()) {
            saldo = rs.getDouble("SALDO");            
        }
        
        cerrarConexion();
        cmd.close();
        rs.close();
        
        return saldo;
    }
    
    public double consultaSaldoCDT(int idInversion) throws ClassNotFoundException, SQLException
    {
        CallableStatement cmd;
        ResultSet rs;
        
        double saldo=0;
        String sql = "EXEC CONSULTA_CDT ?";
        
        abrirConexion();
        
        cmd = miConexion.prepareCall(sql);
        
        cmd.setInt(1, idInversion);
        
        rs = cmd.executeQuery();
        
        if (rs.next()) {
            saldo = rs.getDouble("VALOR_INVERSION");            
        }
        
        cerrarConexion();
        cmd.close();
        rs.close();
        
        return saldo;
    }

    public double consultaSaldoTotal(String nif) throws ClassNotFoundException, SQLException
    {
        CallableStatement cmd;
      
        double saldo=0;
        String sql = "EXEC SALDO_TOTAL ?,  ?";
        
        abrirConexion();
        
        cmd = miConexion.prepareCall(sql);
        
        cmd.setString(1, nif);
        //Registrar un parametro de salida
        cmd.registerOutParameter(2, Types.DOUBLE);
        
        cmd.execute();
        
        //Recoger el valor del parametro de salida
        saldo = cmd.getDouble(2);
        
        cerrarConexion();
        cmd.close();
        
        return saldo;
    }
     
     //Método para crear una inversion
     //Parametro de entrada -> CDT
    public boolean crearInversion(CDT inversion) throws ClassNotFoundException, SQLException
    {
         CallableStatement sentencia;

         int resultado;
         String sql = "EXEC ALTA_INVERSION ?, ?, ?";

         abrirConexion();

         sentencia = miConexion.prepareCall(sql);

         sentencia.setInt(1, inversion.getIdCuenta());
         sentencia.setDouble(2, inversion.getInteresesMensuales());
         sentencia.setDouble(3, inversion.getValorInversion());

         resultado = sentencia.executeUpdate();

         cerrarConexion();
         sentencia.close();

         return (resultado > 0);
     }
     
     //Método para hacer un ingreso
     //Parametro de entrada -> movimiento con los datos de cuenta y cantidad
    public boolean Ingreso(Movimiento m1) throws ClassNotFoundException, SQLException
    {

        CallableStatement sentencia;

        int resultado;
        String sql = "EXEC INGRESO ?,?";

        abrirConexion();

        sentencia = miConexion.prepareCall(sql);

        sentencia.setInt(1, m1.getIdCuenta());
        sentencia.setDouble(2, m1.getCantidad());

        resultado = sentencia.executeUpdate();

        cerrarConexion();
        sentencia.close();

        return (resultado > 0);
    }
     
     //Método para cerrar una inversion
     //Parametro de entrada --> idInversión
     //Parametro de salida --> Exito / no exito
     public boolean cerrarInversion(int idInversion) throws ClassNotFoundException, SQLException
     {
         CallableStatement sentencia;

         boolean exito;
         String sql = "EXEC CERRAR_INVERSION ?;";

         abrirConexion();

         sentencia = miConexion.prepareCall(sql);

         sentencia.setInt(1, idInversion);
         
         exito = sentencia.execute();

         cerrarConexion();
         sentencia.close();

         return exito;
    }
    
    //Método para hacer un retiro
    //Parametro de entrada -> movimiento con los datos de cuenta y cantidad
    public boolean retiro(Movimiento m1) throws ClassNotFoundException, SQLException
    {
        CallableStatement sentencia;

        int resultado;
        String sql = "EXEC RETIRO ?,?";

        abrirConexion();

        sentencia = miConexion.prepareCall(sql);

        sentencia.setInt(1, m1.getIdCuenta());
        sentencia.setDouble(2, m1.getCantidad());

        resultado = sentencia.executeUpdate();

        cerrarConexion();
        sentencia.close();

        return (resultado > 0);
    }
     
     
    /**
     * Metodo de consultar simulacion
     * @param nif
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public double simulacion(String nif) throws ClassNotFoundException, SQLException
    {
        CallableStatement cmd;     

        double simulacion =0;
        String sql = "EXEC SIMULACION ?,  ?";       
        abrirConexion();    
        cmd = miConexion.prepareCall(sql);    
        cmd.setString(1, nif);
        //Registrar un parametro de salida
        cmd.registerOutParameter(2, Types.DOUBLE);
        
        cmd.execute();
        
        //Recoger el valor del parametro de salida
        simulacion = cmd.getDouble(2);
        
        cerrarConexion();
        cmd.close();
        
        return simulacion;
    }
    
    /**
     * Metodo para consultar todas las cuentas
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Cuenta> obtenerCuentas() throws ClassNotFoundException, SQLException
    {
    	List <Cuenta> cuentas = new ArrayList<Cuenta>();
    	
    	abrirConexion();
    	
    	CallableStatement query = miConexion.prepareCall("SELECT * FROM CUENTAS");
    	
    	ResultSet res = query.executeQuery();
    	
    	while(res.next())
    	{
    		int id = res.getInt("idcuenta");
    		double saldo = res.getDouble("saldo");
    		String nif = res.getString("nif");
    		String estado = res.getString("estado");
    		
    		Cuenta cuenta = new Cuenta(id, saldo, nif, EstadoCuenta.valueOf(estado));
    		
    		cuentas.add(cuenta);
    	}
    	
    	cerrarConexion();
    	query.close();
    	
    	return cuentas;
    }
    
    public List<CDT> obtenerCdts() throws ClassNotFoundException, SQLException
    {
    	List <CDT> cdts = new ArrayList<CDT>();
    	
    	abrirConexion();
    	
    	CallableStatement query = miConexion.prepareCall("SELECT * FROM CDT");
    	
    	ResultSet res = query.executeQuery();
    	
    	while(res.next())
    	{
    	    int id = res.getInt("idInversion");
    	    int idCuenta = res.getInt("idCuenta");

    	    LocalDate fApertura = LocalDate.parse(res.getString("fecha_apertura"));
    	    
    	    double intMensuales = res.getDouble("intereses_mensuales");
    	    double vInversion = res.getDouble("valor_inversion");
    	    String estado = res.getString("estado_cdt");
    		
    		CDT cdt = new CDT(id, idCuenta, fApertura, intMensuales, vInversion, estado);
    		
    		cdts.add(cdt);
    	}
    	
    	cerrarConexion();
    	query.close();
    	
    	return cdts;
    }
    

    public List<Map<String, String>> obtenerListadoCuentas() throws ClassNotFoundException, SQLException
    {
    	List<Map<String, String>> datos = new ArrayList<Map<String, String>>();
    	
    	abrirConexion();
    	
    	CallableStatement query = miConexion.prepareCall("SELECT * FROM CUENTAS c JOIN CLIENTES cl ON c.nif = cl.nif");
    	
    	ResultSet res = query.executeQuery();
    	
    	while(res.next())
    	{
    		Map<String, String> fila = new HashMap<String, String>();
    		
    		fila.put("id", String.valueOf(res.getInt("idcuenta")));
    		fila.put("saldo", String.valueOf(res.getDouble("saldo")));
    		fila.put("estado", String.valueOf(res.getString("estado")));
    		
    		fila.put("nif", String.valueOf(res.getString("nif")));
    		fila.put("nombre", String.valueOf(res.getString("nombre")));
    		
    		datos.add(fila);
    	}
    	
    	cerrarConexion();
    	query.close();
    	
    	return datos;
    }
    
    
}
