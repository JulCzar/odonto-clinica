package br.czar.odonto.model;

public class PhysicalPerson extends Person {
	private String cpf;
	private String password;
	private String lastname;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PhysicalPerson))
			return false;
		PhysicalPerson other = (PhysicalPerson) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhysicalPerson = { id: " + getId() + ", name: " + getName() + ", email: " + getEmail()+ ", address: " + getAddress() + "cpf: " + getCpf() + ", password: " + password + ", lastname: " + lastname + " }";
	}
}
