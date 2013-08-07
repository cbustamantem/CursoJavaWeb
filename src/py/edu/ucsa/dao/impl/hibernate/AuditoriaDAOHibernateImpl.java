package py.edu.ucsa.dao.impl.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.edu.ucsa.connections.Conexiones;
import py.edu.ucsa.connections.Drivers;
import py.edu.ucsa.dao.AuditoriaDAO;
import py.edu.ucsa.dto.AuditoriaDTO;

public class AuditoriaDAOHibernateImpl implements AuditoriaDAO
{

	@Override
	public void insertar(AuditoriaDTO a) {
		try
		{
		 Drivers.cargarDrivers();
        
         	Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);         
         	Statement stmt = conPostgres.createStatement();
         	
         	String sql ="insert into sesion (session_id,estado) values ('" + a.getSession_id()+ "','" + a.getEstado() + "' ) ";
         	System.out.println("Ingresando Auditoria: SQL:" + sql);
            int resultado = stmt.executeUpdate(sql);
            System.out.println("Auditoria ingresada :" + resultado);
            /*
            if (resultado)
            {
            	System.out.println("Alumno actualizado exitosamente ");
            }
            else
            {
            	System.out.println("Error al actualizar al alumno");
            }
           */
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
		
		
	}

	
	

}
