package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.City;
import br.czar.odonto.model.State;
import br.czar.odonto.repository.CityRepository;
import br.czar.odonto.repository.Repository;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CityController extends Controller<City> {
  private static final long serialVersionUID = 6606464699712173219L;
  private List<City> cities;

  public CityController() {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.keep("city-to-edit");
    entity = (City) flash.get("city-to-edit");
  }

  @Override
  public City getEntity() {
    if (entity == null) {
      entity = new City();
      entity.setState(new State());
    }
    return entity;
  }

  public List<City> getCities() {
    CityRepository sr = new CityRepository();
    if (cities == null) {
      try {
        cities = sr.findAll();
      } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
      }
    }
    return cities;
  }
  public void destroy(City city) {
    Repository<City> repo = new Repository<City>();
    System.out.println(city);
    try {
      repo.beginTransaction();
      repo.remove(city);
      repo.commitTransaction();
      this.cities = null;

      Util.addInfoMessage("Estado removido com sucesso.");
    } catch (RepositoryException e) {
      e.printStackTrace();
      Util.addErrorMessage("Erro ao remover o Estado.");
    } finally {
      clear();
    }
  }

  public void edit(City s) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    flash.put("city-to-edit", s);
    Util.redirect("/OdontoClinica/cadastro/cidade");
  }
}
