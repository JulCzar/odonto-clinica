package br.czar.odonto.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PhysicalPerson extends Person {
	private static final long serialVersionUID = 4940736015402051156L;
	@Column(unique = true, length = 14, nullable = false)
	private String cpf;
	private String password;
	private String lastname;
	
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
		this.password = password;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "PhysicalPerson = { id: " + getId() + ", name: " + getName() + ", email: " + getEmail()+ ", address: " + getAddress() + ", cpf: " + getCpf() + ", password: " + password + ", lastname: " + lastname + " }";
	}
}
