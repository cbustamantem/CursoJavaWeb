package py.edu.ucsa.jdbc.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import py.edu.ucsa.jdbc.util.Conector;

public class Procedimientos {

	
	public static void createProcedure(Connection c) {
		Statement s;
		
		try 
		{
			// para postgres es varchar, para oracle varchar2
			s = c.createStatement();
			
			String createStmt = "CREATE OR REPLACE FUNCTION sp_crea_alumno(nombre_alumno \"varchar\", ci int4) " +
					" RETURNS int4 AS $BODY$begin " + 
					" INSERT INTO alumnos (nombre, cedula) " +
					" VALUES (nombre_alumno, ci); " + 
					" return 1; " +
					" end$BODY$ " +
					" LANGUAGE 'plpgsql' VOLATILE; ";
			
			s.executeUpdate(createStmt);
			
			s.executeUpdate("ALTER FUNCTION sp_crea_alumno(nombre_alumno \"varchar\", ci int4) OWNER TO cbustamante;");
			
			createStmt = "CREATE OR REPLACE FUNCTION refalumnoscursor() " +
						" RETURNS refcursor AS 	" +
						" $BODY$ DECLARE  ref refcursor; " +
						" BEGIN " +
						" OPEN ref FOR SELECT nombre  FROM alumnos; " +
						"  RETURN ref; "+
						" END; $BODY$  LANGUAGE 'plpgsql' VOLATILE;";
			
			s.executeUpdate(createStmt);
			s.close();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}	
	
	
	    public static void llamarProcedimiento(Connection c) {
	    	try {
	    		c.setAutoCommit(false);
	    		CallableStatement proc = c.prepareCall("{ ? =  call alumnos_upper (?) }");
	    		proc.registerOutParameter(1, Types.INTEGER);
	    		proc.setString(2, "a%");
	    		proc.execute();
	    		
	    		int resultado = proc.getInt(1);	    		
	    		proc.close();
	    		
	    		System.out.println("El valor de retorno del proc es: " + resultado);	    		
	    		c.commit();
	    		c.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	    public static void db_size(Connection c) {
	    	try {
	    		c.setAutoCommit(false);
	    		CallableStatement proc = c.prepareCall("{ ? =  call database_size (?) }");
	    		proc.registerOutParameter(1, Types.BIGINT);
	    		proc.setString(2, "modulo 2");
	    		proc.execute();
	    		
	    		long resultado = proc.getLong(1);
	    		
	    		System.out.println("El valor de retorno del proc es: " + resultado);
	    		
	    		proc.close();
	    		
	    		c.commit();
	    		c.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	    }
	    
	    
	    
	    public static void llamarProcedimientoRS(Connection c) {
	    	try {
	    		c.setAutoCommit(false);
	    		CallableStatement proc = c.prepareCall("{ ? =  call refalumnoscursor () }");
	    		proc.registerOutParameter(1, Types.OTHER);
	    		proc.execute();
	    		ResultSet rs = (ResultSet) proc.getObject(1);
	    		
	    		while (rs.next()) {
	    			System.out.println("Nombre: " + rs.getString(1));
	    		}
	    		rs.close();
	    		
	    		proc.close();
	    		c.commit();
	    		c.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    	    
	    
		public static void main(String[] args) {
			try {
				Connection c = Conector.conectar("postgres");
				
				//createProcedure(c);
				//llamarProcedimiento(c);
				db_size(c);
				
				//llamarProcedimientoRS(c);
				c.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}


}
