package br.czar.odonto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
public class State extends DefaultEntity<State> {
	private static final long serialVersionUID = -8567810764030735286L;
	@Column(unique = true)
	private String name;
	@Size(min = 2, max = 2)
	@Column(unique = true, length = 2)
	private String uf;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "State = { name: " + name + ", uf: " + uf + " }";
	}
}
