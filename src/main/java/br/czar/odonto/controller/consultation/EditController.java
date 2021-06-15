package br.czar.odonto.controller.consultation;

import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.Consultation;
import br.czar.odonto.model.Exam;
import br.czar.odonto.repository.ExamRepository;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Named("consultationEditController")
@ViewScoped
public class EditController extends Controller<Consultation> {
	@Serial
	private static final long serialVersionUID = -1839705494640604823L;
	private List<Exam> exams;

	@Override
	public Consultation getEntity() {
		if (entity == null)
			entity = new Consultation();

		return entity;
	}

	public List<Exam> getExams() {
		ExamRepository er = new ExamRepository();
		if (exams == null) {
			try {
				exams = er.findAll();
			}catch (Exception e) {
				exams = new ArrayList<>();
				e.printStackTrace();
			}
		}
		return exams;
	}

	@Override
	public void store() {
		super.store();
	}
}
