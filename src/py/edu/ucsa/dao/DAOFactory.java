package py.edu.ucsa.dao;

import py.edu.ucsa.dao.impl.ProyectoDAO;
import py.edu.ucsa.dao.impl.ProyectoDAOImpl;
import py.edu.ucsa.dao.impl.hibernate.AlumnoDAOHibernateImpl;
import py.edu.ucsa.dao.impl.hibernate.AuditoriaDAOHibernateImpl;
import py.edu.ucsa.dao.impl.hibernate.AutenticacionDAO;
import py.edu.ucsa.dao.impl.hibernate.AutenticaionDAOHibernateImpl;
import py.edu.ucsa.dao.impl.hibernate.ProfesorDAOHibernateImpl;


public class DAOFactory {
	
	public static ProyectoDAO getProyectoDAO()
	{
		return new ProyectoDAOImpl();
	}
	
	public static AutenticacionDAO getAutenticacionDAO()
	{
		return new AutenticaionDAOHibernateImpl();
	}
	
	public static AlumnoDAO getAlumnoDAO()
	{
		return new AlumnoDAOHibernateImpl();
	}
	
	public static AuditoriaDAO getAuditoriaDAO()
	{
		return new AuditoriaDAOHibernateImpl();
	}
	
	public static ProfesorDAO getProfesorDAO()
	{
		return new ProfesorDAOHibernateImpl();
	}

}
