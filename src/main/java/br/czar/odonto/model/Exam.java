package br.czar.odonto.model;

import javax.persistence.Entity;

@Entity
public class Exam extends DefaultEntity<Exam> {
	private static final long serialVersionUID = -6756862350770908677L;
	private String name;

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
}
