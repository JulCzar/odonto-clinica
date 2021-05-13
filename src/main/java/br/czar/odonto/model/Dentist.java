package br.czar.odonto.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dentist extends DefaultEntity<Dentist> {
	private static final long serialVersionUID = 1483241996299043812L;
	@Transient
	private List<String> specializations;
	private String register;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.ALL })
	@JoinColumn(name = "id_physical_person", unique = true)
	private PhysicalPerson physicalPerson;
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
	public PhysicalPerson getPhysicalPerson() {
		return physicalPerson;
	}
	public void setPhysicalPerson(PhysicalPerson physicalPerson) {
		this.physicalPerson = physicalPerson;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((physicalPerson == null) ? 0 : physicalPerson.hashCode());
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
		if (physicalPerson == null) {
			if (other.physicalPerson != null)
				return false;
		} else if (!physicalPerson.equals(other.physicalPerson))
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
		return "Dentist = {" + "specializations: " + specializations + ", register: " + register + ", person: " + physicalPerson + " }";
	}
}
