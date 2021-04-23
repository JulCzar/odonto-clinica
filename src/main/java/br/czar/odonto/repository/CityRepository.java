package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.City;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CityRepository extends Repository<City> {
  public CityRepository() {
    super(JPAUtil.getEntityManager());
  }
  public CityRepository(EntityManager em) {
    super(em);
  }
  public List<City> findAll() throws RepositoryException {
    EntityManager em = getEntityManager();
    StringBuilder jpql = new StringBuilder();
    jpql.append("SELECT ")
            .append(" c ")
            .append("FROM ")
            .append(" City c ")
            .append("ORDER BY c.name ");

    Query q = em.createQuery(jpql.toString());
    return (List<City>)(q.getResultList());
  }
}
