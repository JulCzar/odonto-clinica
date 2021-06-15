package br.czar.odonto.controller.patient;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.*;
import br.czar.odonto.repository.CityRepository;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("patientListController")
@ViewScoped
public class ListController extends Controller<Patient> {
  @Serial
  private static final long serialVersionUID = -90981056471624046L;
  private static final String FLASH_KEY = "patient-to-edit";
  private List<Patient> patients;


	@Override
  public Patient getEntity() {
    if (entity == null) {
      entity = new Patient();
      entity.setPhone(new Phone());
      entity.setAllergies(new ArrayList<>());
      entity.setPhysicalPerson(new PhysicalPerson());
      entity.setAddress(new Address());
      entity.getAddress().setCity(new City());
      entity.getAddress().getCity().setState(new State());
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
    FlashStorage.setItem(FLASH_KEY, patient);
    Util.redirect("/OdontoClinica/admin/editar/paciente");
  }
}
