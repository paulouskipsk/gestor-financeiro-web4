package br.edu.utfpr.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import br.edu.utfpr.models.User;

@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.login(request.getParameter("username"), request.getParameter("password"));
			response.sendRedirect("home");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao efetuar login");
			response.sendRedirect("login.html");
		}
	}
	/*
	 * private String encriptSha256(String text) throws Exception { try {
	 * MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
	 * messageDigest.update(text.getBytes("UTF-8")); return
	 * messageDigest.digest().toString(); } finally { return new
	 * Random().toString(); }
	 * 
	 * }
	 */
}
