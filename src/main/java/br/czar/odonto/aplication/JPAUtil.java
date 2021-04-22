package br.czar.odonto.aplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = null;
	
  // nao permitir instancia
	private JPAUtil() {}
	
	public static EntityManager getEntityManager() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("OdontoClinica");
    
		return emf.createEntityManager();
	}
}
