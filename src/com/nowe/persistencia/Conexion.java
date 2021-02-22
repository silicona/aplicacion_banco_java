/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nowe.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author Programacion
 */
public class Conexion {
    
    protected Connection miConexion;
    
    public void abrirConexion() throws ClassNotFoundException, SQLException  {
        //Cargar el driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //Definicion del String de conexion
        String stringConexion =  "jdbc:sqlserver://localhost:1433;" +
                     "databaseName=CUENTAS_BANCOS;" +
                     "user=sa;" +
                     //"user=alumno2;" +
                     //"password=alumno2b;" +
                     "password=Pa8722word;" +
                     "encrypt=false;" +
                     "trustServerCertificate=false;" +
                     "loginTimeout=30;";
        //Obtener un objeto de tipo conexion
        miConexion = DriverManager.getConnection(stringConexion);
        
    }
    
    public void cerrarConexion() throws SQLException {
        miConexion.close();
    }
    
}
