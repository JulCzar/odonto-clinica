package br.czar.odonto.controller.dentist;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.*;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Named("dentistRegisterController")
@ViewScoped
public class RegisterController extends Controller<Dentist> {
	@Serial
	private static final long serialVersionUID = -909810564745689156L;
	private List<String> specializations;

	@Override
	public Dentist getEntity() {
		if (entity == null) {
			entity = new Dentist();
			entity.setSpecializations(new ArrayList<>());
			entity.setPhysicalPerson(new PhysicalPerson());
		}
		return entity;
	}

	@Override
	public void store() {
		PhysicalPerson p = getEntity().getPhysicalPerson();

		List<Specialization> SpecializationList = entity.getSpecializations();
		for (String s : specializations)
			SpecializationList.add(new Specialization(s.trim()));

		getEntity().setPhysicalPerson(Security.encript(p));
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
