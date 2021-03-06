package br.czar.odonto.controller.patient;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.*;
import br.czar.odonto.repository.CityRepository;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("editPatientControl")
@ViewScoped
public class EditController extends Controller<Patient> {
	@Serial
	private static final long serialVersionUID = -90981056471624046L;
	private static final String FLASH_KEY = "patient-to-edit";
	private String password, confirmPassword;
	private List<String> allergies;
	private List<Patient> patients;
	private List<City> cities;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return this.confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword){
		this.confirmPassword = confirmPassword;
	}
	public EditController() {
		entity = (Patient) FlashStorage.getItemAndKeep(FLASH_KEY);

		if (entity == null || entity.getAllergies() == null) return;
		for (Allergy a : entity.getAllergies()) {
			getAllergies().add(a.getName());
		}
	}

	public List<String> getAllergies() {
		if (allergies == null) allergies = new ArrayList<>();

		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	@Override
	public void store() {
		List<Allergy> allergyList = entity.getAllergies();
		List<String> allergies = getAllergies();
		
		for (String s : allergies)
			allergyList.add(new Allergy(s.trim()));

		PhysicalPerson p = getEntity().getPhysicalPerson();
		getEntity().setPhysicalPerson(Security.encript(p));
		super.store();
		Util.redirect("/OdontoClinica/admin/lista/paciente");
	}

	public void update() {
		List<Allergy> allergyList = entity.getAllergies();
		List<String> allergies = getAllergies();

		for (String s : allergies)
			if (!allergyList.contains(new Allergy(s.trim())))
				allergyList.add(new Allergy(s.trim()));
	
		allergyList.removeIf(a -> !allergies.contains(a.getName()));

		super.store();
		Util.redirect("/OdontoClinica/admin/lista/paciente");
	}

	public void updatePassword() {
		if (!getPassword().equals(getConfirmPassword())) {
			Util.addInfoMessage("As senhas n??o correspondem");
			return;
		}
		getEntity().getPhysicalPerson().setPassword(getPassword());
		getEntity().setPhysicalPerson(Security.encript(getEntity().getPhysicalPerson()));

		super.store();
		Util.addInfoMessage("Senha atualizada!");
	}

	@Override
	public Patient getEntity() {
		if (entity == null) {
			entity = new Patient();
			entity.setPhone(new Phone());
			entity.setAllergies(new ArrayList<>());
			entity.setPhysicalPerson(new PhysicalPerson());
			entity.setAddress(new Address());
			entity.getAddress().setCity(new City());
			entity.getAddress().getCity().setState(new State());
		}
		return entity;
	}

	public List<Patient> getPatients() {
		PatientRepository pr = new PatientRepository();
		if (patients == null) {
			try {
				patients = pr.findAll();
			}catch (Exception e) {
				patients = new ArrayList<>();
				e.printStackTrace();
			}
		}
		return patients;
	}
	public void destroy(Patient entity) {
		this.entity = entity;
		destroy();
		patients = null;
	}

	public void edit(Patient patient) {
		FlashStorage.setItem(FLASH_KEY, patient);
		Util.redirect("/OdontoClinica/admin/editar/paciente");
	}

	public List<City> getCities() {
		if (cities == null) {
			CityRepository cr = new CityRepository();
			try {
				cities = cr.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}

		return cities
			.stream()
			.filter(c -> c.getState()
				.getId()
				.equals(entity
					.getAddress()
					.getCity()
					.getState()
					.getId()
				))
			.collect(Collectors.toList());
	}

	public List<City> filterCities(String name) {
		return getCities()
			.stream()
			.filter(c -> c.getName()
				.toLowerCase()
				.startsWith(name.toLowerCase())
			)
			.collect(Collectors.toList());
	}
}
