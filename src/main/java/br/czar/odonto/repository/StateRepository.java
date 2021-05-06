package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.State;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class StateRepository extends Repository<State> {
  public StateRepository() {
    super(JPAUtil.getEntityManager());
  }
  public StateRepository(EntityManager em) {
    super(em);
  }
  @SuppressWarnings("unchecked")
  public List<State> findAll() throws RepositoryException {
    EntityManager em = getEntityManager();
    String jpql = "SELECT s FROM State s ORDER BY s.name";

    Query q = em.createQuery(jpql);
    return (List<State>)(q.getResultList());
  }
}
