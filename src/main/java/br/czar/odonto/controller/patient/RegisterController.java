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

@Named("patientRegisterController")
@ViewScoped
public class RegisterController extends Controller<Patient> {
	@Serial
	private static final long serialVersionUID = -90981056471624046L;
	private List<String> allergies;
	private List<Patient> patients;
	private List<City> cities;

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
