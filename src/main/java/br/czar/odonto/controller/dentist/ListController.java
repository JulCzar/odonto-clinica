package br.czar.odonto.controller.dentist;

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
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.City;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.model.Patient;
import br.czar.odonto.repository.CityRepository;
import br.czar.odonto.repository.DentistRepository;

@Named("dentistListController")
@ViewScoped
public class ListController extends Controller<Dentist> {
	@Serial
	private static final long serialVersionUID = 3872792410902756484L;
	private static final String FLASH_KEY = "dentist-to-edit";
	private List<Dentist> dentists;


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
		entity.setActive(false);
		super.store();
		dentists = null;
	}
	public void unlock(Dentist entity) {
		this.entity = entity;
		entity.setActive(true);
		super.store();
		dentists = null;
	}

	public void edit(Dentist patient) {
		FlashStorage.setItem(FLASH_KEY, patient);
		Util.redirect("/OdontoClinica/admin/editar/dentista");
	}
}
