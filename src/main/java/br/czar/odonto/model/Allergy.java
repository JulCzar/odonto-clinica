package br.czar.odonto.model;

import java.io.Serial;

import javax.persistence.Entity;

@Entity
public class Allergy extends DefaultEntity<Allergy> {	
	@Serial
	private static final long serialVersionUID = 3163291234502724058L;
	private String name;

	public Allergy() {}
	public Allergy(String name) {
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
		if (this == obj)
			return true;
		if (!(obj instanceof Allergy))
			return false;
		Allergy other = (Allergy) obj;
		if (name == null)
			return other.name == null;

		return name.equals(other.name);
	}
}
