package br.czar.odonto.model;

import javax.persistence.Entity;
import java.io.Serial;

@Entity
public class Specialization extends DefaultEntity<Specialization> {
	@Serial
	private static final long serialVersionUID = 3163295468502724058L;
	private String name;

	public Specialization() {}
	public Specialization(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Specialization)) return false;
		Specialization other = (Specialization) obj;
		if (name == null) return other.name == null;

		return name.equals(other.name);
	}
}
