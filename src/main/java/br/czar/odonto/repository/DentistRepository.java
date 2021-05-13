package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Dentist;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DentistRepository extends Repository<Dentist> {
  public DentistRepository() {
    super(JPAUtil.getEntityManager());
  }
  public DentistRepository(EntityManager em) {
    super(em);
  }

	@SuppressWarnings("unchecked")
  public List<Dentist> findAll() throws RepositoryException {
    EntityManager em = getEntityManager();

    String jpql = "SELECT d FROM Dentist d ORDER BY d.physicalPerson.name";
    Query q = em.createQuery(jpql);
    return (List<Dentist>)(q.getResultList());
  }

  public List<Dentist> findByEmail(String email) throws RepositoryException{
    try {
      EntityManager em = getEntityManager();

      String jpql = "SELECT d FROM Dentist d WHERE d.physicalPerson.email = :email ORDER BY d.id";
      Query query = em.createQuery(jpql);
      query.setParameter("email",  email  );

      return query.getResultList();
    } catch (Exception e) {
      System.out.println("Erro ao realizar uma consulta ao banco.");
      e.printStackTrace();
      throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
    }

  }
}
