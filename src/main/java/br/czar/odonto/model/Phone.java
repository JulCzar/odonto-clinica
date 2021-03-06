package br.czar.odonto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serial;

@Entity
public class Phone extends DefaultEntity<Phone> {
	@Serial
	private static final long serialVersionUID = -383707362906407719L;

	@Column(length = 4)
	private String area;
	private String number;

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFullNumber() {
		return "("+area+") " + number;
	}

	@Override
	public String toString() {
		return area+ " " +number;
	}
}
