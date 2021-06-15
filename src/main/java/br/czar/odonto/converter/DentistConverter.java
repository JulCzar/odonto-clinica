package br.czar.odonto.converter;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.repository.DentistRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Dentist.class)
public class DentistConverter implements Converter<Dentist> {
  @Override
  public Dentist getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) return null;

		DentistRepository cr = new DentistRepository();
		Dentist city = null;
    try {
      city = cr.find(Integer.valueOf(value.trim()));
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return city;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Dentist value) {
    if (value == null || value.getId() == null)
      return null;
    return value.getId().toString();
  }
}
