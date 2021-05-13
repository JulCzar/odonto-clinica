package br.czar.odonto.controller;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.City;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.repository.CityRepository;
import br.czar.odonto.repository.DentistRepository;

@Named
@ViewScoped
public class DentistController extends Controller<Dentist> {
	@Serial
	private static final long serialVersionUID = 3872792410902756484L;
	private static final String FLASH_KEY = "patient-to-edit";
	private List<Dentist> dentists;
	private List<City> cities;
	private Integer index;

	public DentistController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		entity = (Dentist) flash.get(FLASH_KEY);
	}

	public Integer getIndex() {
		if (index == null) index = 0;
		return index;
	}

	private void setIndex(Integer i) {
		index = i;
	}

	public void increaseIndex() {
		setIndex(getIndex() + 1);
	}

	public void decreaseIndex() {
		if (index == null || index.equals(0)) return;

		setIndex(getIndex() - 1);
	}

	@Override
	public void store() {
		super.store();
		Util.redirect("/OdontoClinica/lista/paciente");
	}

	@Override
	public Dentist getEntity() {
		if (entity == null) {
			entity = new Dentist();
		}
		return entity;
	}

	public List<Dentist> getDentists() {
		DentistRepository pr = new DentistRepository();
		if (dentists == null) {
			try {
				dentists = pr.findAll();
			}catch (Exception e) {
				dentists = new ArrayList<>();
				e.printStackTrace();
			}
		}
		return dentists;
	}
	public void destroy(Dentist entity) {
		this.entity = entity;
		destroy();
		dentists = null;
	}

	public void edit(Dentist patient) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

		flash.put(FLASH_KEY, patient);
		Util.redirect("/OdontoClinica/editar/dentista");
	}

	public List<City> getCities() {
		if (cities == null) {
			CityRepository cr = new CityRepository();
			try {
				cities = cr.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}

		return cities
			.stream()
			.filter(c -> c.getState()
				.getId()
				.equals(entity
					.getPhysicalPerson()
					.getAddress()
					.getCity()
					.getState()
					.getId()
				))
			.collect(Collectors.toList());
	}
}
