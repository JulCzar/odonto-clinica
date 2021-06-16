package br.czar.odonto.model;

import javax.persistence.*;
import java.io.Serial;

@Entity
public class Address extends DefaultEntity<Address> {
	@Serial
	private static final long serialVersionUID = -924620831899993085L;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_city")
	private City city;
	private String number;
	private String street;
	private String department;
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFullAddress() {
		if (street == null) return "";
		if (department == null) return street;
		if (number == null) return street + ", " + department;
		if (city.getName() == null) return street + ", " + department + " " + number;
		if (city.getState() == null) return street + ", " + department + " " + number + " " + city;

		return street + ", " + department + " " + number + " " + city.getName() + "-" + city.getState().getUf();
	}
	@Override
	public String toString() {
		return "Address = {city: " + city + ", number: " + number + ", street: " + street + ", department: "
				+ department + "}";
	}
	
}
