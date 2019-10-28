package br.edu.utfpr.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.persistence.PersistenceUserAndRole;
import br.edu.utfpr.util.Constants;
import br.edu.utfpr.util.PersistenceFactory;

@WebServlet(urlPatterns = { "/usuarios", "/novo-usuario", "/editar-usuario", "/deletar-usuario"})
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().contains("usuarios")) {
			this.doGetUsers(request, response);
		} else if (request.getServletPath().contains("novo-usuario")) {
			this.doGetNewUser(request, response);
		} else if (request.getServletPath().contains("editar-usuario")) {
			this.doGetEditUser(request, response);
		} else if (request.getServletPath().contains("deletar-usuario")) {
			this.doGetDropUser(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().contains("novo-usuario")) {
			this.doPostNewUser(request, response);
		} else if (request.getServletPath().contains("editar-usuario")) {
			this.doPostEditUser(request, response);
		}
	}

// ============================================== doGet Methods ================================================//

	private void doGetUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = PersistenceFactory.getPersistenceUserAndRoleInstance().findAll();
		request.setAttribute("users", users);
		request.getRequestDispatcher("WEB-INF/views/users/users.jsp").forward(request, response);
	}

	private void doGetNewUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Map<String, String> rolesAccess = Constants.RoleAccess.getRolesAccess();
		User user = new User();

		session.setAttribute("action", "novo-usuario");
		session.setAttribute("user", user);
		session.setAttribute("rolesAccess", rolesAccess);
		request.getRequestDispatcher("WEB-INF/views/users/user-new.jsp").forward(request, response);
	}

	private void doGetEditUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, String> rolesAccess = Constants.RoleAccess.getRolesAccess();
		User user = PersistenceFactory.getPersistenceUserAndRoleInstance()
				.find(Long.parseLong(request.getParameter("id")));

		session.setAttribute("action", "editar-usuario");
		session.setAttribute("rolesAccess", rolesAccess);
		session.setAttribute("user", user);
		request.getRequestDispatcher("WEB-INF/views/users/user-edit.jsp").forward(request, response);
	}

	private void doGetDropUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceUserAndRole ur = PersistenceFactory.getPersistenceUserAndRoleInstance();
		User user = PersistenceFactory.getPersistenceUserAndRoleInstance()
				.find(Long.parseLong(request.getParameter("id")));
		try {
			ur.delete(user);
			JOptionPane.showMessageDialog(null, "Usuário Deletado com sucesso");
			response.sendRedirect("usuarios");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover usuário");
			response.sendRedirect("usuarios");			
		}
	}

// ============================================== doPost Methods ================================================//
	
	private void doPostNewUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User(null, 	request.getParameter("usrName"), 
									request.getParameter("usrUsername"),
									request.getParameter("usrPassword"),
									new UserRole(request.getParameter("usrUsername"), 
												 request.getParameter("usrUserRole")));

		if (user.isValid()) {
			PersistenceUserAndRole persistence = PersistenceFactory.getPersistenceUserAndRoleInstance();
			try {
				persistence.save(user);
				JOptionPane.showMessageDialog(null, "usuario salvo com sucesso.");
				response.sendRedirect("usuarios");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao salvar usuario.");
				throw e;
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.getRequestDispatcher("WEB-INF/views/users/user-new.jsp").forward(request, response);
		}
	}

	private void doPostEditUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceUserAndRole pUser = PersistenceFactory.getPersistenceUserAndRoleInstance();		
		
		User user = pUser.find(Long.parseLong(request.getParameter("usrId")));
		UserRole userRole = user.getUserRole();
		userRole.setRole(request.getParameter("usrUserRole"));
		
		user.setName(request.getParameter("usrName"));
		user.setUserRole(userRole);
		//user.setUsername(request.getParameter("usrUsername"));
		if(request.getParameter("usrPassword") != null && request.getParameter("usrPassword").trim() != "") {
			user.setPassword(request.getParameter("usrPassword"));
		}

		if (user.isValid()) {
			try {
				pUser.update(user);
				JOptionPane.showMessageDialog(null, "usuario Alterado");
				response.sendRedirect("usuarios");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao Alterar usuario: "+e);
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.getRequestDispatcher("WEB-INF/views/users/user-edit.jsp").forward(request, response);
		}
	}
}
