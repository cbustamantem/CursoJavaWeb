package py.edu.ucsa.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest
 */
public class ProcesarFormulario extends HttpServlet 
{
	private static final long serialVersionUID = 1L;	 
	private static final String ruc = "ruc";
	private static final String razonsocial ="razonsocial";
	private static final String contrasena = "contrasena";
	private static final String activo ="activo";
	private static final String vencimiento = "vencimiento";
	private static final String tipo ="tipo";
	private static final String udm = "udm";
	private static final String botonSubmit = "botonSubmit";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarFormulario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter pw = response.getWriter();
         pw.print("<html><head><body>" +
                         "<h1>Mostrar Parametros</h1><hr>");
         if(request.getParameter(ruc) != null)
         {
                 pw.print("<br><b>ruc</b> "+ruc);
         }
         
         if(request.getParameter(razonsocial) != null)
         {
                 pw.print("<br><b>Descripción</b> "+razonsocial);
         }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter pw = response.getWriter();
         pw.print("<html><head><body>" +
                         "<h1>Mostrar Parametros</h1><hr>");
       
	}

}
