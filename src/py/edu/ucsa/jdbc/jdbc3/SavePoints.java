package py.edu.ucsa.jdbc.jdbc3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Calendar;

import py.edu.ucsa.jdbc.util.Conector;

public class SavePoints {
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Calendar currenttime=Calendar.getInstance();
		java.sql.Date startdate= new java.sql.Date(
				currenttime.getTime().getTime()
				); 
		
		
		
		Connection conn = Conector.conectar("origen");
		
		PreparedStatement ps = conn.prepareStatement(  "INSERT INTO alumnos " + 
		                "(nombre, cedula) VALUES ( ?,  ? )");
		
		conn.setAutoCommit(false);
//		 Set a conservative transaction isolation level.
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		
		ps.setString( 1, "hola");
		ps.setInt(2, 888888);
		ps.executeUpdate();
		
//		 Set a named savepoint.
		Savepoint svpt = conn.setSavepoint("NewAuthor");
//		 ...
		ps.setString( 1, " Paraguay ");
		ps.setInt(2, 999999);

		ps.executeUpdate();
//		 ...
		conn.rollback(svpt);
//		 ...
//		 The author has been added, but not updated.
		conn.commit();		
	}

}
