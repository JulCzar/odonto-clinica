package br.czar.odonto.controller;

import br.czar.odonto.model.*;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PatientController extends Controller<Patient> {
  private static final long serialVersionUID = -90981056471624046L;
  private List<Patient> patients;
  private State SelectedState;

  @Override
  public Patient getEntity() {
    if (entity == null) {
      PhysicalPerson newPhysPerson = new PhysicalPerson();
      entity = new Patient();
      entity.setPhone(new Phone());
      entity.setAllergies(new ArrayList<>());
      entity.setPhysicalPerson(newPhysPerson);
      entity.getPhysicalPerson().setAddress(new Address());
      entity.getPhysicalPerson().getAddress().setCity(new City());
      entity.getPhysicalPerson().getAddress().getCity().setState(new State());
      entity.getPhysicalPerson().getAddress().setPhysicalPerson(newPhysPerson);
    }
    return entity;
  }

  public List<Patient> getPatients() {
    PatientRepository pr = new PatientRepository();
    if (patients == null) {
      try {
        patients = pr.findAll();
      }catch (Exception e) {
        patients = new ArrayList<>();
        e.printStackTrace();
      }
    }
    return patients;
  }
}
