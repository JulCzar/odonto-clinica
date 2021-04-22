package br.czar.odonto.converter;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.State;
import br.czar.odonto.repository.StateRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = State.class)
public class StateConverter implements Converter<State> {
  @Override
  public State getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) return null;

    StateRepository sr = new StateRepository();
    State state = null;
    try {
      state = sr.find(Integer.valueOf(value.trim()));
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return state;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, State value) {
    if (value == null || value.getId() == null)
      return null;
    return value.getId().toString();
  }
}
