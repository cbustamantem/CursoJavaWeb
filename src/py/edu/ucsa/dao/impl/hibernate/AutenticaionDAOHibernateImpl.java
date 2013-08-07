package py.edu.ucsa.dao.impl.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.Types;
import py.edu.ucsa.connections.*;


import py.edu.ucsa.connections.Conexiones;
import py.edu.ucsa.connections.Drivers;
import py.edu.ucsa.dao.UsuarioDTO;

public class AutenticaionDAOHibernateImpl implements AutenticacionDAO 
{
	@Override
	public UsuarioDTO autenticar(String user, String pass) {
		// TODO Auto-generated method stub
		UsuarioDTO usr = null;
		
		try
		{
		 Drivers.cargarDrivers();
         //Connection conOracle = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_ORACLE);
         Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);
         
         	Statement stmt = conPostgres.createStatement();
            ResultSet rs = stmt.executeQuery("select username,password from usuarios where username='" + user.trim()+ "'" );
            System.out.println("Obteniendo datos de usuario y password");
            
            while(rs.next())
            {
            	String dbUser=rs.getString(1).toString().trim();
            	String dbPassword=rs.getString(2).toString().trim();
            	if (dbUser.equals(user) && dbPassword.equals(pass))
        		{
            		System.out.println("Usuario coincide ");
        			usr = new UsuarioDTO();
        			usr.setUsuario(user);
        			usr.setPassword(pass);
        			return usr;
        		}            	
            	else
            	{
            		System.out.println("Usuario no coincide ");
            	}
            }
           
	     } 
	     catch (ClassNotFoundException e) 
	     {         
	         System.out.println("No se encontro el driver");
	         e.printStackTrace();
	       
	     } 
	     catch (SQLException e) 
	     {
	         System.out.println("No se pudo conectar" +  e.getMessage());
	         e.printStackTrace();
	       
	     } 
		
		/*
		if ("user".equals(user) && "pass".equals(pass))
		{
			usr = new UsuarioDTO();
			usr.setUsuario(user);
			usr.setPassword(pass);			
		}*/
		return usr;			
	}
}
