package py.edu.ucsa.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroLogin
 */
public class FiltroLogin implements Filter {

    /**
     * Default constructor. 
     */
	int requestCount;
	private HttpSession session ;
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		requestCount=0;
	}
	
    public FiltroLogin() {
      
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain filterchain) 
					throws IOException, ServletException {
		
		System.out.println("FILTER>iniciando filtro 1");
		requestCount++;
		request.setAttribute("requestCount", new Integer(requestCount) );
		//OBETENER LA SESSION
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session= httpRequest.getSession(false);
		
		if (session != null)
		{
			System.out.println("FILTER>iniciando filtro 1");
			
			//time out 1 minuto
			Long fechaActual = System.currentTimeMillis() / 1000L;
			System.out.println("FILTER>Asignando Fecha actual:" + fechaActual);
			Long ultimoAcceso= session.getLastAccessedTime();
				
			Long diferencia = fechaActual - ultimoAcceso;
			if (diferencia > 60)
			{
				System.out.println("FILTER> Salir de la session: " + diferencia);
			}
			else
			{
				System.out.println("FILTER> Actualizar ultimo acceso " + diferencia);				
				
			}
			
			
		}
		else
		{
			System.out.println("FILTER>iniciando filtro 1 USUARIO FUERA DE LA SESSION");
			request.getRequestDispatcher("/login.html").forward(request,response);
		}
		
		filterchain.doFilter(request, response);
		System.out.println("FILTER> finalizo filtro 1");
	}

	

}
