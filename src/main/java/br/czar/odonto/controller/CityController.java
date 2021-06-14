package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.City;
import br.czar.odonto.model.State;
import br.czar.odonto.repository.CityRepository;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Named("cityController")
@ViewScoped
public class CityController extends Controller<City> {
  @Serial
  private static final long serialVersionUID = 6606464699712173219L;
  private static final String FLASH_KEY = "city-to-edit";
  private List<City> cities;

  public CityController() {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    entity = (City) flash.get(FLASH_KEY);
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
    CityRepository cr = new CityRepository();
    if (cities == null) {
      try {
        cities = cr.findAll();
      } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
      }
    }
    return cities;
  }
  public void destroy(City city) {
    this.entity = city;
    destroy();
    cities = null;
  }

  public void edit(City city) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

    flash.put(FLASH_KEY, city);
    Util.redirect("/OdontoClinica/admin/editar/cidade");
  }
}
