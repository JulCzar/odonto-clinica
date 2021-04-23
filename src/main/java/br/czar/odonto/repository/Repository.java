package br.czar.odonto.repository;

import br.czar.odonto.aplication.JPAUtil;
import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.DefaultEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Repository<T extends DefaultEntity<T>> {
  protected EntityManager em = null;

  public Repository() {
    super();
    em = JPAUtil.getEntityManager();
  }
  public Repository(EntityManager em) {
    super();
    this.em = em;
  }

  public void beginTransaction() throws RepositoryException {
    try {
      getTransaction().begin();
    } catch (Exception e) {
      System.out.println("Problemas ao iniciar uma transação.");
      e.printStackTrace();
      throw new RepositoryException("Erro ao iniciar o acesso ao banco de dados.");
    }
  }

  public void commitTransaction() throws RepositoryException {
    try {
      getTransaction().commit();
    } catch (Exception e) {
      System.out.println("Problemas ao comitar a transação.");
      e.printStackTrace();
      throw new RepositoryException("Erro ao concluir a operação no banco de dados.");
    }
  }

  public void rollbackTransaction() {
    try {
      getTransaction().rollback();
    } catch (Exception e) {
      System.out.println("Problemas ao concluir uma transação (Rollback).");
      e.printStackTrace();
    }
  }

  public void save(T entity) throws RepositoryException {
    try {
      EntityManager em = getEntityManager();

      em.merge(entity);
    }catch (Exception e) {
      System.out.println("Erro ao salvar " + entity + " - Repository");
      e.printStackTrace();
      throw new RepositoryException("Erro ao Salvar");
    }

  }
  public void remove(T entity) throws RepositoryException {
    try {
      EntityManager em = getEntityManager();
      T obj = em.merge(entity);
      em.remove(obj);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Erro ao remover " + entity + " - Repository");
      throw new RepositoryException("Erro ao Remover");
    }
  }
  public T find(Integer id) throws RepositoryException {
    try {
      // obtendo o tipo da classe de forma generica (a classe deve ser publica)
      final ParameterizedType type = 	(ParameterizedType) getClass().getGenericSuperclass();
      Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];

      T t = (T) getEntityManager().find(tClass, id);
      return t;
    } catch (Exception e) {
      System.out.println("Erro ao executar o método find do Repository");
      e.printStackTrace();
      throw new RepositoryException("Erro ao buscar os dados");
    }
  }

  public EntityManager getEntityManager() {
    return em;
  }

  private EntityTransaction getTransaction() {
    return getEntityManager().getTransaction();
  }
}
