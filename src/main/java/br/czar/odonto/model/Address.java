package br.czar.odonto.model;

import javax.persistence.*;
import java.io.Serial;

@Entity
public class Address extends DefaultEntity<Address> {
	@Serial
	private static final long serialVersionUID = -924620831899993085L;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_city", unique = true)
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
	@Override
	public String toString() {
		return "Address = {city: " + city + ", number: " + number + ", street: " + street + ", department: "
				+ department + "}";
	}
	
}
