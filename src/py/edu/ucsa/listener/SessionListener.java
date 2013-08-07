package py.edu.ucsa.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import py.edu.ucsa.dao.AuditoriaDAO;
import py.edu.ucsa.dao.DAOFactory;
import py.edu.ucsa.dto.AuditoriaDTO;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent evento) {
        //
    	System.out.println("Registrar Inicio de session : " + evento.getSession().getId());
    	
    	AuditoriaDTO auditoria = new AuditoriaDTO();
    	auditoria.setEstado("logueado");
    	auditoria.setSession_id(evento.getSession().getId());
    	DAOFactory.getAuditoriaDAO().insertar(auditoria);
    	System.out.println("Registrar Inicio de session registrado: " + evento.getSession().getLastAccessedTime());    	    
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
