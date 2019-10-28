package br.edu.utfpr.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.utfpr.persistence.PersistenceExpense;
import br.edu.utfpr.persistence.PersistenceRevenue;
import br.edu.utfpr.persistence.PersistenceUser;
import br.edu.utfpr.persistence.PersistenceUserAndRole;
import br.edu.utfpr.persistence.PersistenceUserRole;

public class PersistenceFactory {
	private static EntityManagerFactory emfInstance;
	public static EntityManagerFactory getEntityManagerFactoryInstance() {
		if (emfInstance == null) {
			synchronized (EntityManagerFactory.class) {
				if (emfInstance == null) {
					emfInstance = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
				}
			}
		}
		return emfInstance;
	}

	private static PersistenceUser persistenceUserInstance;
	public static PersistenceUser getPersistenceUserInstance() {
		if (persistenceUserInstance == null) {
			synchronized (PersistenceUser.class) {
				if (persistenceUserInstance == null) {
					persistenceUserInstance = new PersistenceUser();
				}
			}
		}
		return persistenceUserInstance;
	}
	
	private static PersistenceUserAndRole persistenceUserAndRoleInstance;
	public static PersistenceUserAndRole getPersistenceUserAndRoleInstance() {
		if (persistenceUserAndRoleInstance == null) {
			synchronized (PersistenceUserAndRole.class) {
				if (persistenceUserAndRoleInstance == null) {
					persistenceUserAndRoleInstance = new PersistenceUserAndRole();
				}
			}
		}
		return persistenceUserAndRoleInstance;
	}
	
	private static PersistenceUserRole persistenceUserRoleInstance;
	public static PersistenceUserRole getPersistenceUserRoleInstance() {
		if (persistenceUserRoleInstance == null) {
			synchronized (PersistenceUserRole.class) {
				if (persistenceUserRoleInstance == null) {
					persistenceUserRoleInstance = new PersistenceUserRole();
				}
			}
		}
		return persistenceUserRoleInstance;
	}
	
	private static PersistenceExpense persistenceExpenseInstance;
	public static PersistenceExpense getPersistenceExpenseInstance() {
		if (persistenceExpenseInstance == null) {
			synchronized (PersistenceExpense.class) {
				if (persistenceExpenseInstance == null) {
					persistenceExpenseInstance = new PersistenceExpense();
				}
			}
		}
		return persistenceExpenseInstance;
	}
	
	private static PersistenceRevenue persistenceRevenueInstance;
	public static PersistenceRevenue getPersistenceRevenueInstance() {
		if (persistenceRevenueInstance == null) {
			synchronized (PersistenceRevenue.class) {
				if (persistenceRevenueInstance == null) {
					persistenceRevenueInstance = new PersistenceRevenue();
				}
			}
		}
		return persistenceRevenueInstance;
	}
}
