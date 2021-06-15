package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.SessionStorage;
import br.czar.odonto.model.Allergy;
import br.czar.odonto.model.Patient;
import br.czar.odonto.model.Security;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ProfileController extends Controller<Patient> {
	@Serial
	private static final long serialVersionUID = 2122937466427799647L;
	private static final String FLASH_KEY = "logged-user";
	private String password, confirmPassword;
	private boolean editing;

	public ProfileController() {
		setEditing(false);
	}

	@Override
	public Patient getEntity() {
		if (entity == null) {
			PatientRepository pr = new PatientRepository();
			Patient p = (Patient)SessionStorage.getItem(FLASH_KEY);
			try {
				entity = pr.find(p.getId());
			} catch (RepositoryException e) {
				entity = new Patient();
				e.printStackTrace();
			}
		}
		return entity;
	}

	public void changePassword() {
		if (!confirmPassword.equals(password)) Util.addInfoMessage("Senhas não corresponderm");
		if (!confirmPassword.equals(password)) return;

		Patient p = getEntity();
		p.getPhysicalPerson().setPassword(password);
		p.setPhysicalPerson(Security.encript(p.getPhysicalPerson()));
		Util.addInfoMessage("Sua senha foi alterada!");
		super.store();
	}
	public boolean isEditing() {
		return editing;
	}
	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	public void enableEdit() {
		setEditing(true);
	}
	public void disableEdit() {
		setEditing(false);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String password) {
		this.confirmPassword = password;
	}
	public List<String> getAllergies() {
		Object o = SessionStorage.getItem(FLASH_KEY);
		if (o == null) return new ArrayList<>();
		if (o instanceof Patient) return ((Patient)o).getAllergies().stream().map(Allergy::getName).collect(Collectors.toList());
		return new ArrayList<>();
	}
}
