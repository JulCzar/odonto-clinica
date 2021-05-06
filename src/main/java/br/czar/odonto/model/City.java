package br.czar.odonto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serial;

@Entity
public class City extends DefaultEntity<City> {
	@Serial
	private static final long serialVersionUID = -2687606907373481013L;
	private String name;
	private String code;
	@ManyToOne
	private State state;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "City = {name: " + name + ", code: " + code + ", state: " + state + "}";
	}
}
