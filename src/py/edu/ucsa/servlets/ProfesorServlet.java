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
import py.edu.ucsa.dto.ProfesorDTO;
/**
 * Servlet implementation class ProfesorServlet
 */
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfesorServlet() {
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
		Integer  idProfesor=0;
		if (session != null)
		{
			if (request.getParameter("accion") != null)
			{
				accion=request.getParameter("accion");
			}
			if (request.getParameter("profesor") !=null)
			{
				idProfesor= Integer.parseInt(request.getParameter("profesor"));
			}
			//VALIDACION PRINCIPAL
			if (accion.trim().equals("editar"))
			{
				if (request.getParameter("registrar") != null)
				{
					//ACTUALIZAR EL REGISTRO
					modificarProfesores(request,response);			
					listarProfesores(request, response);
				}
				else
				{
					//MOSTRAR FORMULARIO EDICION
					mostrarModificar(request,response,idProfesor);
				}
			}			
			else if (accion.trim().equals("ingresar"))
			{
				if (request.getParameter("registrar") != null)
				{
					//ACTUALIZAR EL REGISTRO
					agregarProfesores(request,response);			
					listarProfesores(request, response);
				}
				else
				{
					//MOSTRAR FORMULARIO EDICION
					request.getRequestDispatcher("agregarProfesor.jsp").forward(request,response);
				}
			}
			else if (accion.trim().equals("eliminar"))
			{
				//TODO: ELIMINAR
			}
			else
			{
				listarProfesores(request, response);
				
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
	protected void listarProfesores(HttpServletRequest request,  HttpServletResponse response)
	{
		try 
		{
			List<ProfesorDTO> listaProfesores = DAOFactory.getProfesorDAO().listar();	
			
			for(int i=0; i < listaProfesores.size(); i++) //para depurar en consola
			{
				System.out.println(listaProfesores.get(i).getNombre());
			}
			
			session.setAttribute("listaProfesores", listaProfesores);		
			request.getRequestDispatcher("listaProfesores.jsp").forward(request,response);
		} 
		catch (ServletException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	protected void mostrarModificar(HttpServletRequest request,  HttpServletResponse response, Integer idProfesor)
	{
		try 
		{
			List<ProfesorDTO> listaProfesores = DAOFactory.getProfesorDAO().listar();	
			
			for(int i=0; i < listaProfesores.size(); i++)
			{
				if (listaProfesores.get(i).getId() == idProfesor.intValue())
				{
					ProfesorDTO profesor = (ProfesorDTO) listaProfesores.get(i);
					session.setAttribute("profesor", profesor);
					System.out.println("Nombre profesor encontrado: " +  profesor.getNombre());
					System.out.println("ID profesor encontrado: " +  profesor.getId());
					System.out.println("Apellido profesor encontrado: " +  profesor.getApellido());
					
				}
			}			
			request.getRequestDispatcher("modificarProfesor.jsp").forward(request,response);
		} 
		catch (ServletException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	protected void modificarProfesores(HttpServletRequest request,  HttpServletResponse response)
	{
		String idProfesor=request.getParameter("idProfesor");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String materia=request.getParameter("materia");
		System.out.println("Profesor a modificar: id" + idProfesor + " nombre:" + nombre + "apellido:" + apellido+ " materia:" + materia);
		ProfesorDTO profesor = new ProfesorDTO();
		profesor.setId(Integer.parseInt(idProfesor));
		profesor.setNombre (nombre);
		profesor.setApellido(apellido);
		profesor.setMateria(materia);
		DAOFactory.getProfesorDAO().actualizar(profesor);
	}
	protected void agregarProfesores(HttpServletRequest request,  HttpServletResponse response)
	{
		//String idProfesor=request.getParameter("idProfesor");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String materia=request.getParameter("apellido");
		System.out.println("Profesor a agregar:  nombre:" + nombre + "apellido:" + apellido + " materia:" + materia);
		ProfesorDTO profesor = new ProfesorDTO();
		profesor.setId(0);
		profesor.setNombre (nombre);
		profesor.setApellido(apellido);		
		profesor.setMateria(materia);
		DAOFactory.getProfesorDAO().insertar(profesor);
	}

}
