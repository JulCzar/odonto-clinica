package br.czar.odonto.model;

import br.czar.odonto.aplication.Util;

import javax.persistence.*;

@Entity
public class PhysicalPerson extends Person {
	private static final long serialVersionUID = 4940736015402051156L;
	@Column(unique = true, length = 14)
	private String cpf;
	private String password;
	private String lastname;
	@OneToOne(mappedBy = "physicalPerson", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address address;

	@OneToOne(mappedBy = "physicalPerson")
	private Patient patient;

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		String last3 = password.substring(password.length() - 3);
		this.password = Util.hash(password + last3);
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient p) {
		patient = p;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PhysicalPerson = { id: " + getId() + ", name: " + getName() + ", email: " + getEmail()+ ", address: " + getAddress() + ", cpf: " + getCpf() + ", password: " + password + ", lastname: " + lastname + " }";
	}
}
