package br.czar.odonto.controller;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.Address;
import br.czar.odonto.model.City;
import br.czar.odonto.model.Patient;
import br.czar.odonto.model.Phone;
import br.czar.odonto.model.PhysicalPerson;
import br.czar.odonto.model.State;
import br.czar.odonto.repository.CityRepository;
import br.czar.odonto.repository.PatientRepository;

@Named
@ViewScoped
public class PatientController extends Controller<Patient> {
  @Serial
  private static final long serialVersionUID = -90981056471624046L;
  private static final String FLASH_KEY = "patient-to-edit";
  private List<Patient> patients;
  private List<City> cities;

  public PatientController() {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    entity = (Patient) flash.get(FLASH_KEY);
  }

  @Override
  public Patient getEntity() {
    if (entity == null) {
      entity = new Patient();
      entity.setPhone(new Phone());
      entity.setAllergies(new ArrayList<>());
      entity.setPhysicalPerson(new PhysicalPerson());
      entity.getPhysicalPerson().setAddress(new Address());
      entity.getPhysicalPerson().getAddress().setCity(new City());
      entity.getPhysicalPerson().getAddress().getCity().setState(new State());
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

  public void edit(Patient patient) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    flash.put(FLASH_KEY, patient);
    Util.redirect("/OdontoClinica/editar/paciente");
  }

  public List<City> getCities() {
    CityRepository cr = new CityRepository();
    if (cities == null) {
      try {
        cities = cr.findAll();
      } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
      }
    }

    return cities
            .stream()
            .filter(c -> c.getState()
                    .getId()
                    .equals(entity
                            .getPhysicalPerson()
                            .getAddress()
                            .getCity()
                            .getState()
                            .getId()
                    ))
            .collect(Collectors.toList());
  }
}
