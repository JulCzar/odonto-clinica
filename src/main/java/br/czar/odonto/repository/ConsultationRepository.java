package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Consultation;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ConsultationRepository extends Repository<Consultation> {
  public ConsultationRepository() {
    super(JPAUtil.getEntityManager());
  }
  public ConsultationRepository(EntityManager em) {
    super(em);
  }

	@SuppressWarnings("unchecked")
	public List<Consultation> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();

		String jpql = "SELECT c FROM Consultation c ORDER BY c.dayHour";
		Query q = em.createQuery(jpql);
		return (List<Consultation>)(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public List<Consultation> findAllOfPatient(Patient p) throws RepositoryException {
		EntityManager em = getEntityManager();

		String jpql = "SELECT c FROM Consultation c WHERE c.patient.id = :patient ORDER BY c.dayHour";
		Query q = em.createQuery(jpql);
		q.setParameter("patient", p.getId());

		return (List<Consultation>)(q.getResultList());
	}
}
