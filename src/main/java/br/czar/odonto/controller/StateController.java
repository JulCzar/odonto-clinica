package br.czar.odonto.controller;

import br.czar.odonto.model.State;
import br.czar.odonto.repository.StateRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class StateController extends Controller<State> {
  private static final long serialVersionUID = 8359431366697498877L;
  private List<State> states;

  @Override
  public State getEntity() {
    if (entity == null)
      entity = new State();
    
    return entity;
  }

  public List<State> getStates() {
    if (states == null) {
      StateRepository sr = new StateRepository();
      try {
        states = sr.findAll();
      }catch (Exception e) {
        states = new ArrayList<>();
        e.printStackTrace();
      }
    }
    return states;
  }
}
