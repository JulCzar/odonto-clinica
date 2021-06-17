package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.SessionStorage;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.model.Patient;
import br.czar.odonto.model.PhysicalPerson;
import br.czar.odonto.model.Security;
import br.czar.odonto.repository.DentistRepository;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.io.Serial;
import java.io.Serializable;

@Named("loginDentist")
@ViewScoped
public class LoginDentistController implements Serializable {
	@Serial
	private static final long serialVersionUID = -2398636258131458545L;
	private static final String FLASH_KEY = "logged-user";
	private Dentist dentist;

	public Dentist getDentist() {
		if (dentist == null) {
			dentist = new Dentist();
			dentist.setPhysicalPerson(new PhysicalPerson());
		}
		return dentist;
	}

	public String getEmail() {
		return getDentist().getPhysicalPerson().getEmail();
	}
	public void setEmail(String email) {
		getDentist().getPhysicalPerson().setEmail(email);
	}
	public String getPassword() {
		return getDentist().getPhysicalPerson().getPassword();
	}
	public void setPassword(String password) {
		getDentist().getPhysicalPerson().setPassword(password);
	}

	public void login() {
		DentistRepository pr = new DentistRepository();
		try {
			Security.encript(getDentist().getPhysicalPerson());
			Dentist d = pr.findByCredentials(getEmail(), getPassword());

			SessionStorage.setItem(FLASH_KEY, d);
			Util.redirect("/OdontoClinica/index");
		} catch (NoResultException e) {
			Util.addErrorMessage("Usuario ou senha incorretos!");
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro Interno no servidor!");
			e.printStackTrace();
		}
	}
}
