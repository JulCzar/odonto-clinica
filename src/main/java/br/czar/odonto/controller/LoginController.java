package br.czar.odonto.controller;

import java.io.Serial;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.SessionStorage;
import br.czar.odonto.model.Patient;
import br.czar.odonto.repository.PatientRepository;

@Named
@SessionScoped
public class LoginController implements Serializable {
  @Serial
  private static final long serialVersionUID = -2398636258131681199L;
  private Patient patient;
  private String login;
  private String senha;

  public Patient getPatient() {
    if (patient == null) {
      patient = new Patient();
    }
    return patient;
  }

  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public String getSenha() {
    return this.senha;
  }
  public void setSenha(String senha) {
    this.senha = senha;
  }

  public void login() {
    PatientRepository pr = new PatientRepository();
    try {
      Patient p = pr.findByEmail(getLogin()).get(0);
      String senha = getSenha();
      String last3 = senha.substring(senha.length() - 3);
      if (p.getPhysicalPerson().getPassword().equals(Util.hash((senha + last3))))
        SessionStorage.setItem("logged-user", p);
      Util.redirect("/OdontoClinica/index");
    } catch (RepositoryException e) {
      Util.addErrorMessage("Usuario ou senha incorretos!");
      e.printStackTrace();
    } catch (IndexOutOfBoundsException e) {
      Util.addErrorMessage("Usuario ou senha incorretos!");
    }
  }
}
