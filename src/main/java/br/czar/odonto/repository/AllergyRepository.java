package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Allergy;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AllergyRepository extends Repository<Allergy> {
	public AllergyRepository() {
		super(JPAUtil.getEntityManager());
	}
	public AllergyRepository(EntityManager em) {
		super(em);
	}
	@SuppressWarnings("unchecked")
	public List<Allergy> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();
		String jpql = "SELECT a FROM Allergy a ORDER BY a.name";

		Query q = em.createQuery(jpql);
		return (List<Allergy>)(q.getResultList());
	}
}
