package br.czar.odonto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.City;

public class CityRepository extends Repository<City> {
  public CityRepository() {
    super(JPAUtil.getEntityManager());
  }
  public CityRepository(EntityManager em) {
    super(em);
  }
  @SuppressWarnings("unchecked")
  public List<City> findAll() throws RepositoryException {
    EntityManager em = getEntityManager();
    String jpql = "SELECT c FROM City c ORDER BY c.name";

    Query q = em.createQuery(jpql);
    return (List<City>)(q.getResultList());
  }
}
