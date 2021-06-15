package br.czar.odonto.model;

import br.czar.odonto.aplication.Util;

import javax.persistence.*;
import java.io.Serial;

@Entity
public class PhysicalPerson extends Person {
	@Serial
	private static final long serialVersionUID = 4940736015402051156L;
	@Column(unique = true, length = 14)
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

	public String getFullName() {
		return super.getName() + " " + lastname;
	}
	@Override
	public String toString() {
		return "PhysicalPerson = { id: " + getId() + ", name: " + getName() + ", email: " + getEmail()+ ", cpf: " + getCpf() + ", password: " + password + ", lastname: " + lastname + " }";
	}
}
