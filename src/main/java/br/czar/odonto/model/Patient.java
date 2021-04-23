package br.czar.odonto.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Patient extends DefaultEntity<Patient>{
	private static final long serialVersionUID = 6160416917272552149L;
	@OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Phone phone;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private PhysicalPerson physicalPerson;
	@Transient
	private List<Allergie> allergies;

	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
		this.phone.setPatient(this);
	}
	public PhysicalPerson getPhysicalPerson() {
		return physicalPerson;
	}
	public void setPhysicalPerson(PhysicalPerson physicalPerson) {
		this.physicalPerson = physicalPerson;
		this.physicalPerson.setPatient(this);
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
