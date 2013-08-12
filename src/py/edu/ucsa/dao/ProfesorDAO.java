package py.edu.ucsa.dao;
import java.util.List;
import py.edu.ucsa.dto.ProfesorDTO;
public interface ProfesorDAO 
{	
	List <ProfesorDTO>  listar();
	public void insertar(ProfesorDTO a);
	public void actualizar(ProfesorDTO a);
	public void eliminar(ProfesorDTO a);
	public ProfesorDTO obtenerProfesor(int id);
}

