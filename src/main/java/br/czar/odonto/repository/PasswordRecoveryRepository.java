package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.PasswordRecovery;
import br.czar.odonto.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PasswordRecoveryRepository extends Repository<PasswordRecovery> {
	public PasswordRecoveryRepository() {
		super(JPAUtil.getEntityManager());
	}
	public PasswordRecoveryRepository(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public PasswordRecovery findByCode(String code) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();

			String jpql = "SELECT p FROM PasswordRecovery p WHERE p.code = :code ORDER BY p.created desc";
			Query query = em.createQuery(jpql);
			query.setParameter("code",  code);

			return (PasswordRecovery)(query.getSingleResult());
		} catch (Exception e) {
			System.out.println("Erro ao realizar uma consulta ao banco.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar uma consulta ao banco.");
		}
	}
}
