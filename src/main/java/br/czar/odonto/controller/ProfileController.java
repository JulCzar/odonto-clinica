package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.SessionStorage;
import br.czar.odonto.model.Allergy;
import br.czar.odonto.model.Patient;
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

	public List<String> getAllergies() {
		Object o = SessionStorage.getItem(FLASH_KEY);
		if (o == null) return new ArrayList<>();
		if (o instanceof Patient) return ((Patient)o).getAllergies().stream().map(Allergy::getName).collect(Collectors.toList());
		return new ArrayList<>();
	}
}
