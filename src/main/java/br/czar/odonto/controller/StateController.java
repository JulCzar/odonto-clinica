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

import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class StateController extends Controller<State> {
  private static final long serialVersionUID = 8359431366697498877L;
  private List<State> states;

  public StateController() {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.keep("state-to-edit");
    entity = (State) flash.get("state-to-edit");
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
  public void destroy(State entity) {
    Repository<State> repo = new Repository<State>();
    System.out.println(entity);
    try {
      repo.beginTransaction();
      repo.remove(entity);
      repo.commitTransaction();
      states = null;
      Util.addInfoMessage("Estado removido com sucesso.");

    } catch (RepositoryException e) {
      e.printStackTrace();
      Util.addErrorMessage("Erro ao remover o Estado.");
    } finally {
      clear();
    }
  }

  public void edit(State s) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    flash.put("state-to-edit", s);
    Util.redirect("/OdontoClinica/cadastro/estado");
  }
}
