package py.edu.ucsa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import py.edu.ucsa.dao.DAOFactory;
import py.edu.ucsa.dao.UsuarioDTO;
import py.edu.ucsa.dto.AlumnoDTO;
/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnoServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(false);
		String accion="";
		Integer  idAlumno=0;
		if (session != null)
		{
			if (request.getParameter("accion") != null)
			{
				accion=request.getParameter("accion");
			}
			if (request.getParameter("alumno") !=null)
			{
				idAlumno= Integer.parseInt(request.getParameter("alumno"));
			}
			//VALIDACION PRINCIPAL
			if (accion.trim().equals("editar"))
			{
				if (request.getParameter("registrar") != null)
				{
					//ACTUALIZAR EL REGISTRO
					modificarAlumnos(request,response);			
					listarAlumnos(request, response);
				}
				else
				{
					//MOSTRAR FORMULARIO EDICION
					mostrarModificar(request,response,idAlumno);
				}
			}			
			else if (accion.trim().equals("ingresar"))
			{
				if (request.getParameter("registrar") != null)
				{
					//ACTUALIZAR EL REGISTRO
					agregarAlumnos(request,response);			
					listarAlumnos(request, response);
				}
				else
				{
					//MOSTRAR FORMULARIO EDICION
					request.getRequestDispatcher("agregarAlumno.jsp").forward(request,response);
				}
			}
			else if (accion.trim().equals("eliminar"))
			{
				//TODO: ELIMINAR
			}
			else
			{
				listarAlumnos(request, response);
				
			}
		}
		else
		{
			System.out.println("Session invalida");
			request.getSession().invalidate();
			request.getRequestDispatcher("login.html").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}	
	//LISTADO DE ALUMNOS
	protected void listarAlumnos(HttpServletRequest request,  HttpServletResponse response)
	{
		try 
		{
			List<AlumnoDTO> listaAlumnos = DAOFactory.getAlumnoDAO().listar();	
			
			for(int i=0; i < listaAlumnos.size(); i++) //para depurar en consola
			{
				System.out.println(listaAlumnos.get(i).getNombre());
			}
			
			session.setAttribute("listaAlumnos", listaAlumnos);		
			request.getRequestDispatcher("listaAlumnos.jsp").forward(request,response);
		} 
		catch (ServletException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	protected void mostrarModificar(HttpServletRequest request,  HttpServletResponse response, Integer idAlumno)
	{
		try 
		{
			List<AlumnoDTO> listaAlumnos = DAOFactory.getAlumnoDAO().listar();	
			
			for(int i=0; i < listaAlumnos.size(); i++)
			{
				if (listaAlumnos.get(i).getId() == idAlumno.intValue())
				{
					AlumnoDTO alumno = (AlumnoDTO) listaAlumnos.get(i);
					session.setAttribute("alumno", alumno);
					System.out.println("Nombre alumno encontrado: " +  alumno.getNombre());
					System.out.println("ID alumno encontrado: " +  alumno.getId());
					System.out.println("Apellido alumno encontrado: " +  alumno.getApellido());
					
				}
			}			
			request.getRequestDispatcher("modificarAlumno.jsp").forward(request,response);
		} 
		catch (ServletException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	protected void modificarAlumnos(HttpServletRequest request,  HttpServletResponse response)
	{
		String idAlumno=request.getParameter("idAlumno");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		System.out.println("Alumno a modificar: id" + idAlumno + " nombre:" + nombre + "apellido:" + apellido);
		AlumnoDTO alumno = new AlumnoDTO();
		alumno.setId(Integer.parseInt(idAlumno));
		alumno.setNombre (nombre);
		alumno.setApellido(apellido);		
		DAOFactory.getAlumnoDAO().actualizar(alumno);
	}
	protected void agregarAlumnos(HttpServletRequest request,  HttpServletResponse response)
	{
		//String idAlumno=request.getParameter("idAlumno");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		System.out.println("Alumno a agregar:  nombre:" + nombre + "apellido:" + apellido);
		AlumnoDTO alumno = new AlumnoDTO();
		alumno.setId(0);
		alumno.setNombre (nombre);
		alumno.setApellido(apellido);		
		DAOFactory.getAlumnoDAO().insertar(alumno);
	}

}
