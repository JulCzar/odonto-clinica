package br.czar.odonto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Patient;

public class PatientRepository extends Repository<Patient> {
  public PatientRepository() {
    super(JPAUtil.getEntityManager());
  }
  public PatientRepository(EntityManager em) {
    super(em);
  }
  public List<Patient> findAll() throws RepositoryException {
    EntityManager em = getEntityManager();
    StringBuilder jpql = new StringBuilder();
    jpql.append("SELECT ")
            .append(" p ")
            .append("FROM ")
            .append(" Patient p ")
            .append("ORDER BY p.name ");

    Query q = em.createQuery(jpql.toString());
    return (List<Patient>)(q.getResultList());
  }
}
