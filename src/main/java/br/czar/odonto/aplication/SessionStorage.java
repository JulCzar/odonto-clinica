package br.czar.odonto.aplication;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionStorage {
	
	private static SessionStorage session = null;
	
	private SessionStorage() {}
	
	public static SessionStorage getInstance() {
		if (session == null)
			session = new SessionStorage();
		
		return session;
	}
	
	private ExternalContext getExternalContext() {
		if (FacesContext.getCurrentInstance().getExternalContext() == null)
			throw new RuntimeException("Este recurso deve ser utilizado apenas em aplicacoes WEB.");
		
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public Object getItem(String key) {
		return getExternalContext().getSessionMap().get(key);
	}
	
	public void setItem(String key, Object value) {
		getExternalContext().getSessionMap().put(key, value);
	}
	
	public void clear() {
		getExternalContext().invalidateSession();
	}
}
