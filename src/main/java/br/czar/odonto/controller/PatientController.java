package br.czar.odonto.controller;

import br.czar.odonto.model.Patient;
import br.czar.odonto.model.Phone;
import br.czar.odonto.model.PhysicalPerson;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@SessionScoped
public class PatientController extends Controller<Patient> {
  private static final long serialVersionUID = -90981056471624046L;

  @Override
  public Patient getEntity() {
    if (entity == null) {
      entity = new Patient();
      entity.setPhone(new Phone());
      entity.setPhysicalPerson(new PhysicalPerson());
      entity.setAllergies(new ArrayList<>());
    }
    return entity;
  }
}
