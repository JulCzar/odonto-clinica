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

	@SuppressWarnings("unchecked")
  public List<Patient> findAll() throws RepositoryException {
    EntityManager em = getEntityManager();

    String jpql = "SELECT p FROM Patient p ORDER BY p.physicalPerson.name";
    Query q = em.createQuery(jpql);
    return (List<Patient>)(q.getResultList());
  }

	@SuppressWarnings("unchecked")
  public List<Patient> findByEmail(String email) throws RepositoryException{
    try {
      EntityManager em = getEntityManager();

      String jpql = "SELECT p FROM Patient p WHERE p.physicalPerson.email = :email ORDER BY p.id";
      Query query = em.createQuery(jpql);
      query.setParameter("email",  email  );

      return (List<Patient>)(query.getResultList());
    } catch (Exception e) {
      System.out.println("Erro ao realizar uma consulta ao banco.");
      e.printStackTrace();
      throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
    }
  }
}
