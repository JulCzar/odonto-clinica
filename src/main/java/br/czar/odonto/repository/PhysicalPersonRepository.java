package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.PhysicalPerson;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class PhysicalPersonRepository extends Repository<PhysicalPerson> {
	public PhysicalPersonRepository() {
		super(JPAUtil.getEntityManager());
	}
	public PhysicalPersonRepository(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<PhysicalPerson> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();

		String jpql = "SELECT p FROM PhysicalPerson p ORDER BY p.name";
		Query q = em.createQuery(jpql);
		return (List<PhysicalPerson>)(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public PhysicalPerson findByEmail(String email) throws RepositoryException, NoResultException {
		try {
			EntityManager em = getEntityManager();

			String jpql = "SELECT p FROM PhysicalPerson p WHERE p.email LIKE :email";

			Query query = em.createQuery(jpql);
			query.setParameter("email",  email);

			return (PhysicalPerson)(query.getSingleResult());
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
