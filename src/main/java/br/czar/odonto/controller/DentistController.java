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
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.model.City;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.repository.CityRepository;
import br.czar.odonto.repository.DentistRepository;

@Named
@ViewScoped
public class DentistController extends Controller<Dentist> {
	@Serial
	private static final long serialVersionUID = 3872792410902756484L;
	private static final String FLASH_KEY = "dentist-to-edit";
	private List<Dentist> dentists;

	public DentistController() {
		entity = (Dentist) FlashStorage.getItemAndKeep(FLASH_KEY);
	}

	@Override
	public void store() {
		super.store();
		Util.redirect("/OdontoClinica/admin/lista/dentista");
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
		FlashStorage.setItem(FLASH_KEY, patient);
		Util.redirect("/OdontoClinica/admin/editar/dentista");
	}
}
