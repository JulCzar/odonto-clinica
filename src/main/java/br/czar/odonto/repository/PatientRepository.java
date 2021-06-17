package br.czar.odonto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
  public List<Patient> findByName(String name) throws RepositoryException {
		EntityManager em = getEntityManager();

		String jpql = "SELECT p FROM Patient p WHERE UPPER(p.physicalPerson.name) LIKE UPPER(:name) ORDER BY p.physicalPerson.name";

		Query q = em.createQuery(jpql);
		q.setParameter("name", "%"+name+"%");

		return (List<Patient>)(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Patient findByEmail(String email) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();

			String jpql = "SELECT p FROM Patient p WHERE p.physicalPerson.email = :email ORDER BY p.id";
			Query query = em.createQuery(jpql);
			query.setParameter("email",  email  );

			return (Patient)(query.getSingleResult());
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}

	@SuppressWarnings("unchecked")
	public Patient findByCredentials(String email, String password) throws RepositoryException, NoResultException {
		try {
			EntityManager em = getEntityManager();

			String jpql = "SELECT p " +
				"FROM Patient p " +
				"WHERE p.physicalPerson.email = :email " +
				"AND p.physicalPerson.password = :senha " +
				"AND p.active = true " +
				"ORDER BY p.id";
			Query query = em.createQuery(jpql);
			query.setParameter("email",  email);
			query.setParameter("senha",  password);

			return (Patient)(query.getSingleResult());
		} catch (NoResultException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}
}
