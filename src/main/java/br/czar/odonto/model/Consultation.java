package br.czar.odonto.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import java.io.Serial;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Consultation extends DefaultEntity<Consultation> {
	@Serial
	private static final long serialVersionUID = 3097618125821011323L;
	private LocalDateTime dayHour;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Patient patient;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Dentist dentist;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Exam exam;
	private String status;

	public Consultation() {
		this.status = "Em Aberto";
	}

	public LocalDateTime getDayHour() {
		return dayHour;
	}

	public String getDateString() {
		return dayHour.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
	}

	public void setDayHour(LocalDateTime dayHour) {
		this.dayHour = dayHour;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Dentist getDentist() {
		return dentist;
	}

	public void setDentist(Dentist dentist) {
		this.dentist = dentist;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
}
