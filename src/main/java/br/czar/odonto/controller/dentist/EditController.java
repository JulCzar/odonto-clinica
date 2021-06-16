package br.czar.odonto.controller.dentist;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.*;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Named("dentistEditController")
@ViewScoped
public class EditController extends Controller<Dentist> {
	@Serial
	private static final long serialVersionUID = -909810564745689156L;
	private static final String FLASH_KEY = "dentist-to-edit";
	private List<String> specializations;

	public EditController() {
		entity = (Dentist) FlashStorage.getItemAndKeep(FLASH_KEY);

		if (entity == null || entity.getSpecializations() == null) return;
		for (Specialization a : entity.getSpecializations()) {
			getSpecializations().add(a.getName());
		}
	}

	@Override
	public Dentist getEntity() {
		if (entity == null) {
			entity = new Dentist();
			entity.setSpecializations(new ArrayList<>());
			entity.setPhysicalPerson(new PhysicalPerson());
		}
		return entity;
	}

	public void update() {
		List<Specialization> specializationsList = entity.getSpecializations();

		for (String s : specializations)
			if (!specializationsList.contains(new Specialization(s.trim())))
				specializationsList.add(new Specialization(s.trim()));

		specializationsList.removeIf(s -> !specializations.contains(s.getName()));

		super.store();
		Util.redirect("/OdontoClinica/admin/lista/dentista");
	}

	public List<String> getSpecializations() {
		if (specializations == null)
			specializations = new ArrayList<>();

		return specializations;
	}

	public void setSpecializations(List<String> specializations) {
		this.specializations = specializations;
	}
}
