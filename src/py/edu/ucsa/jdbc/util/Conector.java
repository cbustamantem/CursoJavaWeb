package py.edu.ucsa.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

/**
 * Clase que se encarga de devolver una conexi�n a partir de un archivo de configuraci�n
 * @author Pablo
 *
 */
public class Conector {
	
	/**
	 * 
	 * Conecta a la base de datos usando los parametros obtenidos del archivo de
	 * configuracion nombrado file
	 * 
	 * @parm file nombre del archivo.
	 * @return la conexion a base de datos.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public static Connection conectar(String file) throws SQLException,
			ClassNotFoundException {
		
		//Properties prop = new Properties();

		PropertyResourceBundle prop = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(file);
		// prop.load(new
		// FileInputStream(getClass().getClassLoader().getResource(file).getFile());

		String driverName = (String) prop.getString("Driver");
		String url = (String) prop.getString("Url");
		String userName = (String) prop.getString("UserName");
		String pass = (String) prop.getString("Password");

		Class.forName(driverName);

		Connection con = DriverManager.getConnection(url, userName, pass);

		return con;
	}

}
