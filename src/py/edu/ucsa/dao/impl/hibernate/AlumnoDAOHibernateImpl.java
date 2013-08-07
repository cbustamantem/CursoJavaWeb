package py.edu.ucsa.dao.impl.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.edu.ucsa.connections.Conexiones;
import py.edu.ucsa.connections.Drivers;
import py.edu.ucsa.dao.AlumnoDAO;
import py.edu.ucsa.dao.UsuarioDTO;
import py.edu.ucsa.dto.AlumnoDTO;

public class AlumnoDAOHibernateImpl implements AlumnoDAO
{

	@Override
	public List<AlumnoDTO> listar() {
		List<AlumnoDTO> listaAlumnos = new ArrayList <>(); 
		try
		{
			Drivers.cargarDrivers();        
         	Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);         
         	Statement stmt = conPostgres.createStatement();
            ResultSet rs = stmt.executeQuery("select id, nombre, apellido from alumnos " );
            System.out.println("Obteniendo lista de alumnos");
            
            while(rs.next())
            {
            	
            	AlumnoDTO alumno= new AlumnoDTO();
            	alumno.setId(rs.getInt(1));
    			alumno.setNombre(rs.getString(2));
    			alumno.setApellido(rs.getString(3));
    			listaAlumnos.add(alumno);
            }
            return listaAlumnos;
           
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
	public void insertar(AlumnoDTO a) {

		try
		{
		 Drivers.cargarDrivers();
        
         	Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);         
         	Statement stmt = conPostgres.createStatement();
         	
         	String sql ="insert into alumnos (nombre,apellido) values ('" + a.getNombre()+ "','" + a.getApellido() + "' ) ";
         	System.out.println("Ingresando Alumno: SQL:" + sql);
            boolean resultado = stmt.execute(sql);
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

	@Override
	public void actualizar(AlumnoDTO a) {
		
		try
		{
		 Drivers.cargarDrivers();
        
         	Connection conPostgres = Conexiones.obtenerConexion(Conexiones.DBMS_TYPE_POSTGRES);         
         	Statement stmt = conPostgres.createStatement();
         	
         	String sql ="update alumnos set nombre='" + a.getNombre()+ "', apellido='" + a.getApellido() + "' where id=" + a.getId() ;
         	System.out.println("Actualizando Alumno: SQL:" + sql);
            boolean resultado = stmt.execute(sql );
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

	@Override
	public void eliminar(AlumnoDTO a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AlumnoDTO obtenerAlumno(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
