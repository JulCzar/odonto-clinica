package br.czar.odonto.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.czar.odonto.aplication.SessionStorage;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.Patient;


@Named
@RequestScoped
public class SessionController {
	public static final String LOGGED_USER = "logged-user";
	public static final SessionStorage sessionStorage = SessionStorage.getInstance();
	
	public Patient getUserdata() {
		Object obj = sessionStorage.getItem(LOGGED_USER);
		
		if (obj == null) obj = new Patient();
		
		return (Patient)obj;
	}
	
	public boolean isLogged() {
		return getUserdata().getPhysicalPerson() != null; 
	}
	
	public String getUserName() {
		Patient p = getUserdata();
		String nome = p.getPhysicalPerson() == null?"Usuario":p.getPhysicalPerson().getName();
		return nome;
	}
	
	public void logout() {
		sessionStorage.setItem(LOGGED_USER, null);
		Util.redirect("/OdontoClinica/login");
	}
}
