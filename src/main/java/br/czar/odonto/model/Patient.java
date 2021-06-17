package br.czar.odonto.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	@OneToOne(
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "id_endereco", unique = true)
	private Address address;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Allergy> allergies;
	private boolean active;

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
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

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getActiveStatus() {
		return active?"Ativo":"Inativo";
	}

	@Override
	public String toString() {
		return "Patient = { id: " + getId() + ", name: " + physicalPerson.getName() + ", email: " + physicalPerson.getEmail()+ ", address: " + getAddress() + ", cpf: " + physicalPerson.getCpf() + ", password: " + physicalPerson.getPassword() + ", lastname: " + physicalPerson.getLastname() + ", phone: " + phone + ", allergies: " + allergies + " }";
	}
}
