package br.czar.odonto.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Patient extends DefaultEntity<Patient>{
	private static final long serialVersionUID = 6160416917272552149L;
	@OneToOne
	private Phone phone;
	@OneToOne
	private PhysicalPerson physicalPerson;
	@Transient
	private List<Allergie> allergies;
	
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
	public List<Allergie> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<Allergie> allergies) {
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "Patient = { id: " + getId() + ", name: " + physicalPerson.getName() + ", email: " + physicalPerson.getEmail()+ ", address: " + physicalPerson.getAddress() + ", cpf: " + physicalPerson.getCpf() + ", password: " + physicalPerson.getPassword() + ", lastname: " + physicalPerson.getLastname() + ", phone: " + phone + ", allergies: " + allergies + " }";
	}
}
