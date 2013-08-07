package py.edu.ucsa.dao.impl;

import java.util.List;

import py.edu.ucsa.dto.Proyecto;

public interface ProyectoDAO 
{
	void insert (Proyecto p);
	
	void update (Proyecto p);
	
	void delete (Proyecto p);
	
	List<Proyecto> getProyectos();
	
	Proyecto getProyectoById(Long id);
		
	void  getProyectoDAO();

}
