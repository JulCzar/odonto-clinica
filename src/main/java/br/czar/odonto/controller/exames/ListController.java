package br.czar.odonto.controller.exames;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.Exam;
import br.czar.odonto.repository.ExamRepository;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Named("examListController")
@ViewScoped
public class ListController extends Controller<Exam> {
	@Serial
	private static final long serialVersionUID = 3196763417367935152L;
	private static final String FLASH_KEY = "exam-to-edit";
	private List<Exam> exams;

	@Override
	public Exam getEntity() {
		if (entity == null) entity = new Exam();

		return entity;
	}

	public List<Exam> getExams() {
		ExamRepository pr = new ExamRepository();
		if (exams == null)  {
			try {
				exams = pr.findAll();
			}catch (Exception e) {
				exams = new ArrayList<>();
				e.printStackTrace();
			}
		}
		return exams;
	}

	public void destroy(Exam entity) {
		this.entity = entity;
		destroy();
		exams = null;
	}

	public void edit(Exam patient) {
		FlashStorage.setItem(FLASH_KEY, patient);
		Util.redirect("/OdontoClinica/admin/editar/exame");
	}
}
