package br.czar.odonto.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.czar.odonto.model.Patient;

@Named
@SessionScoped
public class LoginController implements Serializable {
  private static final long serialVersionUID = -2398636258131681199L;
  private Patient patient;

  public Patient getPatient() {
    if (patient == null)
      this.patient = new Patient();

    return patient;
  }

  public void login() {
    System.out.println(patient);
  }
}
