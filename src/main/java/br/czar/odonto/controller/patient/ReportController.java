package br.czar.odonto.controller.patient;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.Patient;
import br.czar.odonto.repository.PatientRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("patientReportController")
@ViewScoped
public class ReportController implements Serializable {
	@Serial
	private static final long serialVersionUID = -4522335819599729555L;

	private String filter;
	private List<Patient> patients;

	public void search() {
		PatientRepository repo = new PatientRepository();
		try {
			setPatients(repo.findByName(filter));
		} catch (RepositoryException e) {
			setPatients(null);
			e.printStackTrace();
		}
	}

	public void clear() {
		patients = null;
	}

	public void generate() {
		Util.directRedirect("/OdontoClinica/paciente/relatorio?nome="+getFilter());
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<Patient> getPatients() {
		if (patients == null) patients = new ArrayList<>();
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
}
