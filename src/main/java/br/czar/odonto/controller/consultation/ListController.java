package br.czar.odonto.controller.consultation;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.Consultation;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.repository.ConsultationRepository;
import br.czar.odonto.repository.DentistRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Named("consultationListController")
@ViewScoped
public class ListController extends Controller<Consultation> {
	@Serial
	private static final long serialVersionUID = -1839705494640604823L;
	private static final String FLASH_KEY = "consultation-to-edit";
	private List<Consultation> consultations;

	@Override
	public Consultation getEntity() {
		if (entity == null)
			entity = new Consultation();

		return entity;
	}

	public List<Consultation> getConsultations() {
		ConsultationRepository pr = new ConsultationRepository();
		if (consultations == null) {
			try {
				consultations = pr.findAll();
			}catch (Exception e) {
				consultations = new ArrayList<>();
				e.printStackTrace();
			}
		}
		return consultations;
	}
	public void destroy(Consultation entity) {
		this.entity = entity;
		destroy();
		consultations = null;
	}

	public void edit(Consultation patient) {
		FlashStorage.setItem(FLASH_KEY, patient);
		Util.redirect("/OdontoClinica/admin/consultas/detalhes");
	}
}
