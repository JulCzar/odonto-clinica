package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.*;
import br.czar.odonto.repository.PatientRepository;
import br.czar.odonto.repository.Repository;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PatientController extends Controller<Patient> {
  private static final long serialVersionUID = -90981056471624046L;
  private List<Patient> patients;

  public PatientController() {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    entity = (Patient) flash.get("patient-to-edit");
  }

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
  public void destroy(Patient entity) {
    this.entity = entity;
    destroy();
    patients = null;
  }

  public void edit(Patient s) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    flash.put("patient-to-edit", s);
    Util.redirect("/OdontoClinica/editar/paciente");
  }
}
