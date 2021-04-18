package br.czar.odonto.model;

import java.util.List;

public class Dentist {
	private List<String> specializations;
	private String register;
	private PhysicalPerson person;
	public List<String> getSpecializations() {
		return specializations;
	}
	public void setSpecializations(List<String> specializations) {
		this.specializations = specializations;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public PhysicalPerson getPerson() {
		return person;
	}
	public void setPerson(PhysicalPerson person) {
		this.person = person;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((register == null) ? 0 : register.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Dentist))
			return false;
		Dentist other = (Dentist) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (register == null) {
			if (other.register != null)
				return false;
		} else if (!register.equals(other.register))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dentist = {" + "specializations: " + specializations + ", register: " + register + ", person: " + person + " }";
	}
}
