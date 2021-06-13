package br.czar.odonto.aplication.storage;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Request {
	private Request() {}

	private static ExternalContext getExtCont() {
		return FacesContext.getCurrentInstance() .getExternalContext();
	}
	public static String getQuery(String key) {
		return getExtCont().getRequestParameterMap().get(key);
	}
}
