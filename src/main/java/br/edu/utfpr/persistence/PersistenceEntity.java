package br.edu.utfpr.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.edu.utfpr.util.PersistenceFactory;
import br.edu.utfpr.util.PersistenceUtils;

@SuppressWarnings("unchecked")
public abstract class PersistenceEntity<T, PK> {
	private final EntityManagerFactory factory;
	private Class<?> genericClass;

	/* ==================== constructors ===================== */
	public PersistenceEntity() {
		this(PersistenceFactory.getEntityManagerFactoryInstance());
	}

	public PersistenceEntity(EntityManagerFactory factory) {
		this.factory = factory;
		// PARA UTILIZAÇÃO DO GENERICS
		this.genericClass = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	/* ======================= CRUD ========================== */
	public Object executeQuery(String query, Object...params) {
		Query createQuery = PersistenceUtils.getEntityManager().createQuery(query);
		
		for(int i = 0; i < params.length; i++) {
			createQuery.setParameter(i, params[i]);
		}
		return createQuery.getResultList();
	}
	
	public T getByProperty(String propertyName, String propertyValue){
		String queryString = "SELECT o FROM " + this.genericClass.getName() + " o where o." + propertyName + " = :param";		
		
		Query query = PersistenceUtils.getEntityManager().createQuery(queryString);		
		query.setParameter("param", propertyValue);
		
	    List<T> queryResult = query.getResultList();
	    T returnObject = null;

	    if (!queryResult.isEmpty()){
	        returnObject = queryResult.get(0);
	    }

	    return returnObject;
	}

	public List<T> findAll(){
		return PersistenceUtils.getEntityManager().createQuery("from "+this.genericClass.getName()).getResultList();
	}
	
	public T find(PK pk){
		return (T) PersistenceUtils.getEntityManager().find(this.genericClass, pk);
	}

	public void save(T entity) {
		try {
			PersistenceUtils.startTransaction();
			PersistenceUtils.getEntityManager().persist(entity);
			PersistenceUtils.commit();
		} catch (Exception e) {
			PersistenceUtils.rollback();
			throw e;
		}
	}

	public void update(T entity) {
		try {
			PersistenceUtils.startTransaction();
			PersistenceUtils.getEntityManager().merge(entity);
			PersistenceUtils.commit();
		} catch (Exception e) {
			PersistenceUtils.rollback();
			throw e;
		}
	}
	
	public void delete(T entity) {
		try {
			PersistenceUtils.startTransaction();
				PersistenceUtils.getEntityManager().remove(entity);
			PersistenceUtils.commit();
		}catch (Exception e) {
			PersistenceUtils.rollback();
			throw e;
		}
	}
}
