package br.edu.utfpr.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.util.PersistenceFactory;
import br.edu.utfpr.util.PersistenceUtils;

public class PersistenceUserAndRole {

	public void save(User user) {
		try {
			PersistenceUtils.startTransaction();
			PersistenceUtils.getEntityManager().persist(user);
			PersistenceUtils.getEntityManager().persist(user.getUserRole());
			PersistenceUtils.commit();
		} catch (Exception e) {
			PersistenceUtils.rollback();
			throw e;
		}
	}
	
	public void update(User user) {
		try {
			UserRole userRole = PersistenceFactory.getPersistenceUserRoleInstance().find(user.getUserRole().getUsername());
			userRole.setRole(user.getUserRole().getRole());
	
			PersistenceUtils.startTransaction();
				PersistenceUtils.getEntityManager().merge(user);
				PersistenceUtils.getEntityManager().merge(userRole);
			PersistenceUtils.commit();
		} catch (Exception e) {
			PersistenceUtils.rollback();
		}
	}
	
	public void delete(User user) {
		try {
			PersistenceUtils.startTransaction();
			PersistenceUtils.getEntityManager().remove(user.getUserRole());
			PersistenceUtils.getEntityManager().remove(user);
			PersistenceUtils.commit();
		} catch (Exception e) {
			PersistenceUtils.rollback();
			throw e;
		}
	}
	
	public User find(Long id) {
		PersistenceUser pu = PersistenceFactory.getPersistenceUserInstance();
		PersistenceUserRole ur = PersistenceFactory.getPersistenceUserRoleInstance();
		
		User user = pu.find(id);
		UserRole userRole = ur.find(user.getUsername());
		user.setUserRole(userRole);	
		
		return user;
	}
	
	public List<User> findAll() {
		PersistenceUser pu = PersistenceFactory.getPersistenceUserInstance();
		PersistenceUserRole ur = PersistenceFactory.getPersistenceUserRoleInstance();
		List<User> users;
		List<User> usersDTO = new ArrayList<User>();
		
		users = pu.findAll();
		for (User user : users) {
			UserRole userRole = ur.find(user.getUsername());
			user.setUserRole(userRole);
			usersDTO.add(user);
		}
		
		return usersDTO;
	}
}
