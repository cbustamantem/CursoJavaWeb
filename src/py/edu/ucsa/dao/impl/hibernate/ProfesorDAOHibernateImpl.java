package py.edu.ucsa.dao.impl.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.edu.ucsa.connections.Conexiones;
import py.edu.ucsa.connections.Drivers;
import py.edu.ucsa.dao.ProfesorDAO;
import py.edu.ucsa.dto.ProfesorDTO;

public class ProfesorDAOHibernateImpl implements ProfesorDAO
{

	@Override
	public List<ProfesorDTO> listar() {
		List<ProfesorDTO> listaProfesores = new ArrayList <>(); 
		try
		{
			Drivers.cargarDrivers();        
         	Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);         
         	Statement stmt = conPostgres.createStatement();
            ResultSet rs = stmt.executeQuery("select idprofesor, nombre, apellido, materia from profesores " );
            System.out.println("Obteniendo lista de profesores");
            
            while(rs.next())
            {
            	
            	ProfesorDTO profesor= new ProfesorDTO();
            	profesor.setId(rs.getInt(1));
    			profesor.setNombre(rs.getString(2));
    			profesor.setApellido(rs.getString(3));
    			profesor.setMateria(rs.getString(4));
    			listaProfesores.add(profesor);
            }
            return listaProfesores;
           
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
		return null;
	}

	@Override
	public void insertar(ProfesorDTO a) {

		try
		{
		 Drivers.cargarDrivers();
        
         	Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);         
         	Statement stmt = conPostgres.createStatement();
         	
         	String sql ="insert into profesores(nombre,apellido,materia) values ('" + a.getNombre()+ "','" + a.getApellido() +  "','" + a.getMateria() + "') ";
         	System.out.println("Ingresando Profesor: SQL:" + sql);
            int resultado = stmt.executeUpdate(sql);
            /*
            if (resultado)
            {
            	System.out.println("Profesor actualizado exitosamente ");
            }
            else
            {
            	System.out.println("Error al actualizar al profesor");
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

	@Override
	public void actualizar(ProfesorDTO a) {
		
		try
		{
		 Drivers.cargarDrivers();
        
         	Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);         
         	Statement stmt = conPostgres.createStatement();
         	
         	String sql ="update profesores set nombre='" + a.getNombre()+ "', apellido='" + a.getApellido() + "' , materia='" + a.getMateria() + "' where idprofesor=" + a.getId() ;
         	System.out.println("Actualizando Profesor: SQL:" + sql);
            boolean resultado = stmt.execute(sql );
            /*
            if (resultado)
            {
            	System.out.println("Profesor actualizado exitosamente ");
            }
            else
            {
            	System.out.println("Error al actualizar al profesor");
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

	@Override
	public void eliminar(ProfesorDTO a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProfesorDTO obtenerProfesor(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
