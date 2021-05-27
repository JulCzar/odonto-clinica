package br.czar.odonto.aplication.storage;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

public class SessionStorage {
	private static final RuntimeException NOT_WEB_EXCEPTION = new RuntimeException(
		"Este recurso deve ser utilizado apenas em aplicacoes WEB."
	);
	
	private SessionStorage() {}

	private static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	private static Map<String, Object> getSessionMap() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		if (ec == null) throw NOT_WEB_EXCEPTION;
		
		return ec.getSessionMap();
	}

	public static Object getItem(String key) {
		return getSessionMap().get(key);
	}

	public static void setItem(String key, Object value) {
		getSessionMap().put(key, value);
	}
	
	public static void clear() {
		getExternalContext().invalidateSession();
	}
}
