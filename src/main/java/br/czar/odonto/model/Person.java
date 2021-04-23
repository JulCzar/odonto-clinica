package br.czar.odonto.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends DefaultEntity<Person> {
	private static final long serialVersionUID = -6279361218657015098L;
	private String name;
	private String email;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Person = { name: " + name + ", email: " + email + ", address: " + " }";
	}
}
