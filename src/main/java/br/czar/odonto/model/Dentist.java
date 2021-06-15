package br.czar.odonto.model;

import javax.persistence.*;
import java.io.Serial;
import java.util.List;

@Entity
public class Dentist extends DefaultEntity<Dentist> {
	@Serial
	private static final long serialVersionUID = 1483241996299043812L;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Specialization> specializations;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
	@JoinColumn(name = "id_telefone", unique = true)
	private Phone phone;
	private String register;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name = "id_physical_person", unique = true)
	private PhysicalPerson physicalPerson;
	public List<Specialization> getSpecializations() {
		return specializations;
	}
	public void setSpecializations(List<Specialization> specializations) {
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
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
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
			return other.register == null;
		}
		return register.equals(other.register);
	}
	@Override
	public String toString() {
		return "Dentist = {phone: "+ phone + ", specializations: " + specializations + ", register: " + register + ", person: " + physicalPerson + " }";
	}
}
