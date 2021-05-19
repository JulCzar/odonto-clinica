package br.czar.odonto.model;

import javax.persistence.*;
import java.io.Serial;
import java.util.List;

@Entity
public class Patient extends DefaultEntity<Patient>{
	@Serial
	private static final long serialVersionUID = 6160416917272552149L;
	@OneToOne(
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
		},
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "id_telefone", unique = true)
	private Phone phone;
	@OneToOne(
		cascade = {
			CascadeType.PERSIST,
			CascadeType.ALL
		},
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "id_physical_person", unique = true)
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
