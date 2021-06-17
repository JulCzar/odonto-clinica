package br.czar.odonto.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.czar.odonto.aplication.storage.SessionStorage;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.model.Patient;
import br.czar.odonto.model.PhysicalPerson;


@Named
@RequestScoped
public class SessionController {
	public static final String LOGGED_USER = "logged-user";
	
	public PhysicalPerson getUserdata() {
		Object obj = SessionStorage.getItem(LOGGED_USER);
		
		if (obj == null) return null;
		if (obj instanceof Dentist)
			return ((Dentist)obj).getPhysicalPerson();
		
		return ((Patient)obj).getPhysicalPerson();
	}

	public boolean isDentist() {
		Object user = SessionStorage.getItem(LOGGED_USER);

		if (user == null) return false;
		return (user instanceof Dentist);
	}

	public boolean isPatient() {
		Object user = SessionStorage.getItem(LOGGED_USER);

		if (user == null) return false;
		return (user instanceof Patient);
	}
	
	public boolean isLogged() {
		return getUserdata() != null;
	}
	
	public String getUserName() {
		PhysicalPerson p = getUserdata();
		return p == null?"Usuario":p.getName();
	}
	
	public void logout() {
		SessionStorage.setItem(LOGGED_USER, null);
		Util.redirect("/OdontoClinica/login");
	}
}
