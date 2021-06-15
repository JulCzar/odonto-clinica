package br.czar.odonto.converter;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Patient;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Patient.class)
public class PatientConverter implements Converter<Patient> {
  @Override
  public Patient getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) return null;

		PatientRepository cr = new PatientRepository();
		Patient city = null;
    try {
      city = cr.find(Integer.valueOf(value.trim()));
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return city;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Patient value) {
    if (value == null || value.getId() == null)
      return null;
    return value.getId().toString();
  }
}
