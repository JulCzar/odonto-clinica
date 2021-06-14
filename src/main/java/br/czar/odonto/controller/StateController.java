package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.State;
import br.czar.odonto.repository.Repository;
import br.czar.odonto.repository.StateRepository;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class StateController extends Controller<State> {
  private static final String FLASH_KEY = "state-to-edit";
  @Serial
  private static final long serialVersionUID = 8359431366697498877L;
  private List<State> states;

  public StateController() {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    entity = (State) flash.get(FLASH_KEY);
  }

  @Override
  public State getEntity() {
    if (entity == null)
      entity = new State();

    return entity;
  }

  public List<State> getStates() {
    StateRepository sr = new StateRepository();
    if (states == null) {
	    try {
	    	states = sr.findAll();
	    }catch (Exception e) {
	      e.printStackTrace();
	      return new ArrayList<>();
	    }
    }
    return states;
  }
  public void destroy(State state) {
    this.entity = state;
    destroy();
    states = null;
  }

  public void edit(State state) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    flash.put(FLASH_KEY, state);
    Util.redirect("/OdontoClinica/admin/editar/estado");

  }

  public List<State> filterStates(String name) {
  	String filterName = name.toLowerCase();
  	return getStates().stream().filter(s -> {
  		String stateName = s.getName().toLowerCase();
  		String UF = s.getUf().toLowerCase();
  		if (stateName.startsWith(filterName)) return true;

			return UF.startsWith(filterName);
		}).collect(Collectors.toList());
	}
}
