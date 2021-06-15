package br.czar.odonto.controller.exames;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.model.Exam;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;

@Named("examEditController")
@ViewScoped
public class EditController extends Controller<Exam> {
	@Serial
	private static final long serialVersionUID = 3196763417367935152L;
	private static final String FLASH_KEY = "exam-to-edit";

	public EditController() {
		entity = (Exam) FlashStorage.getItemAndKeep(FLASH_KEY);
	}

	@Override
	public Exam getEntity() {
		if (entity == null) entity = new Exam();

		return entity;
	}

	@Override
	public void store() {
		super.store();
		Util.redirect("/OdontoClinica/admin/lista/exames");
	}
}
