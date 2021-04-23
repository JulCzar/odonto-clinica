package br.czar.odonto.converter;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.City;
import br.czar.odonto.repository.CityRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = City.class)
public class CityConverter implements Converter<City> {
  @Override
  public City getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) return null;

    CityRepository sr = new CityRepository();
    City state = null;
    try {
      state = sr.find(Integer.valueOf(value.trim()));
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return state;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, City value) {
    if (value == null || value.getId() == null)
      return null;
    return value.getId().toString();
  }
}
