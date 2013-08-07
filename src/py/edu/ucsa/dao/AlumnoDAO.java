package py.edu.ucsa.dao;
import java.util.List;
import py.edu.ucsa.dto.AlumnoDTO;
public interface AlumnoDAO {	
	List <AlumnoDTO>  listar();
	public void insertar(AlumnoDTO a);
	public void actualizar(AlumnoDTO a);
	public void eliminar(AlumnoDTO a);
	public AlumnoDTO obtenerAlumno(int id);
}
