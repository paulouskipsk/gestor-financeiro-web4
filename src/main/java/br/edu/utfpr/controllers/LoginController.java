package br.edu.utfpr.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.edu.utfpr.model.User;
import br.edu.utfpr.persistence.PersistenceUser;
import br.edu.utfpr.util.Constants;
import br.edu.utfpr.util.FlashMessage;
import br.edu.utfpr.util.PersistenceFactory;

@WebServlet(urlPatterns = {"/login","/logout"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getAttribute("accessDenied") == null) {
			if(request.getServletPath().contains(Constants.Routes.LOGOUT)){
				HttpSession session = request.getSession(false);	
				if(session != null){
					session.invalidate();
				}
				request.getRequestDispatcher(Constants.Routes.ROOT_PATH_VIEWS+"login.jsp").forward(request, response);
			}else {
				if(request.getSession().getAttribute("isLogged") == null) {
					request.getRequestDispatcher(Constants.Routes.ROOT_PATH_VIEWS + "login.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher(Constants.Routes.HOME);
				}
			}
		}else {
			FlashMessage.addMessage("danger", "Acesso não permitido. É preciso estar Logado para acessar esta função.");
			request.removeAttribute("accessDenied");
			request.setAttribute("flash.messages", FlashMessage.getMessages());
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getAttribute("accessDenied") == null) {
			try {
				request.login(request.getParameter("username"), request.getParameter("password"));
				
				HttpSession session = request.getSession();
				PersistenceUser persistence = PersistenceFactory.getPersistenceUserInstance();
				User user = persistence.getByProperty("username", request.getParameter("username"));
				
				response.addCookie(this.getLogAccess(request, user));
				response.addCookie(this.getLastServerInitialization());
				
				session.setAttribute("isLogged", true);
				response.sendRedirect(Constants.Routes.HOME);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao efetuar login :" +e);
				response.sendRedirect("login");
			}
		}else {
			FlashMessage.addMessage("danger", "Acesso não permitido. É preciso estar Logado para acessar esta função.");
			request.removeAttribute("accessDenied");
			request.setAttribute("flash.messages", FlashMessage.getMessages());
			response.sendRedirect("login");
		}
	}
	
	private Cookie getLogAccess(HttpServletRequest request, User user) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String log = null;
		
		for (Cookie c : cookies) {
			if(c.getName().equals("sessionLogAccess")) {
				cookie = c;
				break;
			}
		}
		
		if(cookie == null) {
			cookie = new Cookie("sessionLogAccess", null);
			log = "";
		}else {
			log = cookie.getValue();
		}
		String data = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(new Date());
		
		log += "Acesso:"+data+"_Usuario:"+user.getName()+"::";
		
		cookie.setMaxAge(60*60*24*90); //tempo de vida 90 dias apos o ultimo acesso
		cookie.setHttpOnly(true);
		cookie.setComment("Cookie-para-registro-de-acesso-de-usuarios");
		cookie.setSecure(false);
		cookie.setValue(log);
		
		return cookie;
	}
	
	private Cookie getLastServerInitialization() {
		if(getServletContext().getAttribute("lastServerInitialization") == null) {
			getServletContext().setAttribute("lastServerInitialization", Constants.LAST_SERVER_INITIALIZATION);
		}
		
		Cookie cookie = new Cookie("lastServerInitialization", null);
		cookie.setMaxAge(-1); //tempo de vida ate o browser ser fechado
		cookie.setHttpOnly(false);
		cookie.setComment("Hora-em-que-o-servidor-foi-iniciado-pela-ultima-vez");
		cookie.setSecure(false);
		cookie.setValue(getServletContext().getAttribute("lastServerInitialization").toString());
		return cookie;
	}
}













