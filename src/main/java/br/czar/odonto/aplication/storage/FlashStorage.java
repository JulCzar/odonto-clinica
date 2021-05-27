package br.czar.odonto.aplication.storage;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FlashStorage {
	private static final RuntimeException NOT_WEB_EXCEPTION = new RuntimeException(
		"Este recurso deve ser utilizado apenas em aplicacoes WEB."
	);

	private FlashStorage() {}

	private static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	private static Flash getFlash() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		if (ec == null)
			throw NOT_WEB_EXCEPTION;

		return ec.getFlash();
	}

	public static Object getItem(String key) 	{
		return getFlash().get(key);
	}

	public static Object getItemAndKeep(String key) 	{
		Flash f = getFlash();
		f.keep(key);
		return f.get(key);
	}

	public static void setItem(String key, Object value) {
		getFlash().put(key, value);
	}

	public static void clear() {
		getExternalContext().invalidateSession();
	}
}
