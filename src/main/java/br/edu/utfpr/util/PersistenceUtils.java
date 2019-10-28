package br.edu.utfpr.util;

import javax.persistence.EntityManager;

public class PersistenceUtils {
	//EAGER STARTED PersistenceUtils.getEntityManager()
	private static EntityManager entityManager = PersistenceFactory.getEntityManagerFactoryInstance().createEntityManager();
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static void startTransaction() {
		entityManager.getTransaction().begin();
	}

	public static void commit() {
		entityManager.getTransaction().commit();
	}

	public static void rollback() {
		entityManager.getTransaction().rollback();
	}

	public static void close() {
		entityManager.close();
	}
}
