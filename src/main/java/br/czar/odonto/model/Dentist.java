package br.czar.odonto.model;

import javax.persistence.*;
import java.io.Serial;
import java.util.List;

@Entity
public class Dentist extends DefaultEntity<Dentist> {
	@Serial
	private static final long serialVersionUID = 1483241996299043812L;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Specialization> specializations;
	private String register;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "id_physical_person", unique = true)
	private PhysicalPerson physicalPerson;
	private boolean active;

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
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
	public String getActiveStatus() {
		return active?"Ativo":"Inativo";
	}

	@Override
	public String toString() {
		return "Dentist = {specializations: " + specializations + ", register: " + register + ", person: " + physicalPerson + " }";
	}
}
