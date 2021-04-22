package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.DefaultEntity;
import br.czar.odonto.repository.Repository;

import java.io.Serializable;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable {
  private static final long serialVersionUID = 7067708678056325040L;
  protected T entity;

  public Controller() { super(); }

  public abstract T getEntity();

  public void setEntity(T entity) {
    this.entity = entity;
  }

  public void store() {
    Repository<T> repo = new Repository<T>();

    try {
      repo.beginTransaction();
      repo.save(getEntity());
      repo.commitTransaction();

      Util.addInfoMessage("Cadastro realizado com sucesso.");
    } catch (RepositoryException e) {
      repo.rollbackTransaction();
      Util.addErrorMessage("Cadastro não realizado.");
    } finally {
      clear();
    }
  }

  public void destroy() {
    Repository<T> repo = new Repository<T>();
    try {
      repo.beginTransaction();
      repo.remove(getEntity());
      repo.commitTransaction();

      Util.addInfoMessage("Paciente removido com sucesso.");
    } catch (RepositoryException e) {
      e.printStackTrace();
      Util.addErrorMessage("Paciente removido com sucesso.");
    } finally {
      clear();
    }
  }

  public void clear() {
    entity = null;
  }

  public void update(T entity) {
    System.out.println("Entrou no editar");
    setEntity(entity);
  }
}
