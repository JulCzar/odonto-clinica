package br.czar.odonto.converter;

import br.czar.odonto.model.Allergy;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Allergy.class)
public class AllergyConverter implements Converter<Allergy> {
  @Override
  public Allergy getAsObject(FacesContext context, UIComponent component, String value) {
    return new Allergy(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Allergy value) {
    if (value == null || value.getId() == null)
      return null;
    return value.getId().toString();
  }
}
