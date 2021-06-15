package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Exam;
import br.czar.odonto.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class ExamRepository extends Repository<Exam> {
  public ExamRepository() {
    super(JPAUtil.getEntityManager());
  }
  public ExamRepository(EntityManager em) {
    super(em);
  }

	@SuppressWarnings("unchecked")
  public List<Exam> findAll() throws RepositoryException {
    EntityManager em = getEntityManager();

    String jpql = "SELECT e FROM Exam e ORDER BY e.name";
    Query q = em.createQuery(jpql);
    return (List<Exam>)(q.getResultList());
  }
}
