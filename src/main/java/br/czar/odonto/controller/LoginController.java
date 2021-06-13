package br.czar.odonto.controller;

import java.io.Serial;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.SessionStorage;
import br.czar.odonto.model.Patient;
import br.czar.odonto.model.PhysicalPerson;
import br.czar.odonto.model.Security;
import br.czar.odonto.repository.PatientRepository;

@Named("authenticate")
@ViewScoped
public class LoginController implements Serializable {
  @Serial
  private static final long serialVersionUID = -2398636258131681199L;
  private Patient patient;

  public Patient getPatient() {
    if (patient == null) {
      patient = new Patient();
      patient.setPhysicalPerson(new PhysicalPerson());
    }
    return patient;
  }

  public String getEmail() {
    return getPatient().getPhysicalPerson().getEmail();
  }
  public void setEmail(String email) {
    getPatient().getPhysicalPerson().setEmail(email);
  }
  public String getPassword() {
    return getPatient().getPhysicalPerson().getPassword();
  }
  public void setPassword(String password) {
		getPatient().getPhysicalPerson().setPassword(password);
  }

  public void login() {
    PatientRepository pr = new PatientRepository();
    try {
			Security.encript(getPatient().getPhysicalPerson());
      Patient p = pr.findByCredentials(getEmail(), getPassword());

			SessionStorage.setItem("logged-user", p);
      Util.redirect("/OdontoClinica/index");
    } catch (NoResultException e) {
			Util.addErrorMessage("Usuario ou senha incorretos!");
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro Interno no servidor!");
			e.printStackTrace();
		}
  }
}
