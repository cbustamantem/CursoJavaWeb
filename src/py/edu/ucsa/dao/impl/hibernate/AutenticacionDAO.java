package py.edu.ucsa.dao.impl.hibernate;

import py.edu.ucsa.dao.UsuarioDTO;

public interface AutenticacionDAO 
{
	UsuarioDTO autenticar(String user, String pass);

}
