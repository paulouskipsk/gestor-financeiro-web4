package br.edu.utfpr.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.edu.utfpr.model.Expense;
import br.edu.utfpr.model.Revenue;
import br.edu.utfpr.persistence.PersistenceRevenue;
import br.edu.utfpr.util.Format;
import br.edu.utfpr.util.PersistenceFactory;

@WebServlet(urlPatterns = { "/receitas", "/nova-receita", "/editar-receita", "/deletar-receita" })
public class RevenuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getServletPath().contains("receitas")) {
			this.doGetRevenues(request, response);
		} else if (request.getServletPath().contains("nova-receita")) {
			this.doGetNewRevenue(request, response);
		} else if (request.getServletPath().contains("editar-receita")) {
			this.doGetEditRevenue(request, response);
		} else if (request.getServletPath().contains("deletar-receita")) {
			this.doGetDropRevenue(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().contains("nova-receita")) {
			this.doPostNewRevenue(request, response);
		} else if (request.getServletPath().contains("editar-receita")) {
			this.doPostEditRevenue(request, response);
		}
	}
// ============================================== doGet Methods ================================================//

	private void doGetRevenues(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Revenue> revenues = PersistenceFactory.getPersistenceRevenueInstance().findAll();
		request.setAttribute("revenues", revenues);
		request.getRequestDispatcher("WEB-INF/views/revenues/revenues.jsp").forward(request, response);
	}

	private void doGetNewRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Revenue revenue = new Revenue();
		request.setAttribute("action", "nova-receita");
		request.setAttribute("revenue", revenue);
		request.getRequestDispatcher("WEB-INF/views/revenues/revenue-new.jsp").forward(request, response);
	}

	private void doGetEditRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("id") != null) {
			Revenue revenue = PersistenceFactory.getPersistenceRevenueInstance().find(Long.parseLong(request.getParameter("id")));
			request.setAttribute("revenue", revenue);
			request.setAttribute("action", "editar-receita");
			request.getRequestDispatcher("WEB-INF/views/revenues/revenue-edit.jsp").forward(request, response);
		} else {
			JOptionPane.showMessageDialog(null, "Receita não informada");
		}
	}

	private void doGetDropRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersistenceRevenue persistence = PersistenceFactory.getPersistenceRevenueInstance();
		Revenue revenue = persistence.find(Long.parseLong(request.getParameter("id")));

		if (revenue.isValid() && revenue.getId() != null) {
			try {
				persistence.delete(revenue);
				JOptionPane.showMessageDialog(null, "Receita deletada com sucesso");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao deletar Receita");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Receita invalida");
		}
		response.sendRedirect("receitas");
	}

// ============================================== doPost Methods ================================================//

	private void doPostNewRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Revenue revenue = new Revenue(  null, 
										request.getParameter("revDescription"),
										Double.parseDouble(request.getParameter("revValueReceiveble")),
										Format.formatStringToDate(request.getParameter("revDateReceiveble")),
										request.getParameter("revReceivebled") == null ? "F" : "T");

		if (revenue.isValid()) {
			PersistenceRevenue persistence = PersistenceFactory.getPersistenceRevenueInstance();
			try {
				persistence.save(revenue);
				JOptionPane.showMessageDialog(null, "Receita salva com sucesso.");
				HttpSession session = request.getSession();
				session.removeAttribute("revenue");
				response.sendRedirect("receitas");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao salvar Receita. " + e);
				HttpSession session = request.getSession();
				session.setAttribute("nova-receita", revenue);
				response.sendRedirect("nova-receita");
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("nova-receita", revenue);
			response.sendRedirect("nova-receita");
		}
	}

	private void doPostEditRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceRevenue persistence = PersistenceFactory.getPersistenceRevenueInstance();
		Revenue revenue = persistence.find(Long.parseLong(request.getParameter("revId")));

		revenue.setDescription(request.getParameter("revDescription"));
		revenue.setValueReceiveble(Double.parseDouble(request.getParameter("revValueReceiveble")));
		revenue.setDateReceiveble(Format.formatStringToDate(request.getParameter("revDateReceiveble")));
		revenue.setReceivebled(request.getParameter("revReceivebled") == null ? "F" : "T");

		if (revenue.isValid()) {
			try {
				persistence.update(revenue);
				JOptionPane.showMessageDialog(null, "Receita Alterada com sucesso.");
				HttpSession session = request.getSession();
				session.removeAttribute("revenue");
				response.sendRedirect("receitas");
			} catch (Exception e) {
				HttpSession session = request.getSession();
				session.setAttribute("revenue", revenue);
				JOptionPane.showMessageDialog(null, "Erro ao salvar Receita.");
				response.sendRedirect("editar-receita");
				throw e;
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("revenue", revenue);
			response.sendRedirect("WEB-INF/views/revenues/revenue-new.jsp");
		}
	}
}
