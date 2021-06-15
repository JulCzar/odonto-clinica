package br.czar.odonto.controller.consultation;

import br.czar.odonto.aplication.Util;
import br.czar.odonto.aplication.storage.FlashStorage;
import br.czar.odonto.controller.Controller;
import br.czar.odonto.model.*;
import br.czar.odonto.repository.DentistRepository;
import br.czar.odonto.repository.ExamRepository;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("consultationEditController")
@ViewScoped
public class EditController extends Controller<Consultation> {
	@Serial
	private static final long serialVersionUID = -1839705494640604823L;
	private static final String FLASH_KEY = "consultation-to-edit";
	private List<Exam> exams;
	private List<Dentist> dentists;
	private List<Patient> patients;

	public EditController() {
		entity = (Consultation) FlashStorage.getItemAndKeep(FLASH_KEY);
	}

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

	public void update() {
		super.store();
		Util.redirect("/OdontoClinica/admin/visitas/agenda");
	}

	public void cancel() {
		getEntity().setStatus(ConsultationStatus.CANCELADO);
		super.store();
		Util.redirect("/OdontoClinica/admin/visitas/agenda");
	}

	public void complete() {
		getEntity().setStatus(ConsultationStatus.CONCLUIDO);
		super.store();
		Util.redirect("/OdontoClinica/admin/visitas/agenda");
	}

	public List<Dentist> getDentists() {
		DentistRepository er = new DentistRepository();
		if (dentists == null) {
			try {
				dentists = er.findAll();
			}catch (Exception e) {
				dentists = new ArrayList<>();
				e.printStackTrace();
			}
		}
		return dentists;
	}

	public List<Patient> getPatients() {
		PatientRepository er = new PatientRepository();
		if (patients == null) {
			try {
				patients = er.findAll();
			}catch (Exception e) {
				patients = new ArrayList<>();
				e.printStackTrace();
			}
		}
		return patients;
	}

	public List<Patient> filterPatients(String name) {
		return getPatients()
			.stream()
			.filter(c -> c
				.getPhysicalPerson()
				.getName()
				.toLowerCase()
				.startsWith(name.toLowerCase())
			)
			.collect(Collectors.toList());
	}

	public LocalDate getMinDate() {
		return LocalDate.now();
	}
}
