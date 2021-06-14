package br.czar.odonto.model;

import br.czar.odonto.repository.AllergyRepository;

import javax.persistence.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends DefaultEntity<Patient>{
	@Serial
	private static final long serialVersionUID = 6160416917272552149L;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
	@JoinColumn(name = "id_telefone", unique = true)
	private Phone phone;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name = "id_physical_person", unique = true)
	private PhysicalPerson physicalPerson;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Allergy> allergies;

	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public PhysicalPerson getPhysicalPerson() {
		return physicalPerson;
	}
	public void setPhysicalPerson(PhysicalPerson physicalPerson) {
		this.physicalPerson = physicalPerson;
	}
	public List<Allergy> getAllergies() {
		if (allergies == null) allergies = new ArrayList<>();
		return allergies;
	}
	public void setAllergies(List<Allergy> allergies) {
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "Patient = { id: " + getId() + ", name: " + physicalPerson.getName() + ", email: " + physicalPerson.getEmail()+ ", address: " + physicalPerson.getAddress() + ", cpf: " + physicalPerson.getCpf() + ", password: " + physicalPerson.getPassword() + ", lastname: " + physicalPerson.getLastname() + ", phone: " + phone + ", allergies: " + allergies + " }";
	}
	@PostUpdate
	public void clear_allergies() {
		System.out.println("here Model");
		try {
			AllergyRepository ar = new AllergyRepository();
			ar.clear_allergies();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
