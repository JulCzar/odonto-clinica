package br.czar.odonto.controller.exames;

import br.czar.odonto.model.Exam;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ExamsController implements Serializable {
	@Serial
	private static final long serialVersionUID = 3196763417367935152L;
	private Exam exam;
	private List<Exam> examList;

	public Exam getExam() {
		if (exam == null) exam = new Exam();

		return exam;
	}

	public List<Exam> getExamList() {
		return examList;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
}
