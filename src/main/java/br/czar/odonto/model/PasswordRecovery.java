package br.czar.odonto.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;

@Entity
public class PasswordRecovery extends DefaultEntity<PasswordRecovery> {
	@Serial
	private static final long serialVersionUID = 7857336307257775351L;

	@ManyToOne
	@JoinColumn(name = "id_person", nullable = false)
	private PhysicalPerson user;
	private boolean used;
	private String code;

	public PhysicalPerson getUser() {
		return user;
	}

	public void setUser(PhysicalPerson user) {
		this.user = user;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
