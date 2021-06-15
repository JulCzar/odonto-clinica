package br.czar.odonto.controller;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.aplication.Util;
import br.czar.odonto.model.DefaultEntity;
import br.czar.odonto.repository.Repository;

import java.io.Serial;
import java.io.Serializable;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable {
  @Serial
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
    } catch (RepositoryException e) {
      e.printStackTrace();
      Util.addErrorMessage("Problema ao remover Paciente.");
    } finally {
      clear();
    }
  }

  public void clear() {
    entity = null;
  }

  public void update() {
		Repository<T> repo = new Repository<T>();

		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			Util.addErrorMessage("Cadastro não realizado.");
		} finally {
			clear();
		}
  }
}
