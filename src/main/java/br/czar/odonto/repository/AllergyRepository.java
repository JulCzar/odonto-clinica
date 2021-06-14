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

	public void clear_allergies() {
		String queryStr = "DELETE FROM allergy as al WHERE al.id IN ( SELECT DISTINCT al.id FROM allergy as al FULL OUTER JOIN patient_allergy as pa ON al.id = pa.allergies_id WHERE al.id IS NULL OR pa.allergies_id IS NULL OR al.id <> NEW.id );".formatted();
		System.out.println("here");
		try {
			Query query = em.createNativeQuery(queryStr);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
