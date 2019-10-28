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
import br.edu.utfpr.persistence.PersistenceExpense;
import br.edu.utfpr.util.Format;
import br.edu.utfpr.util.PersistenceFactory;

@WebServlet(urlPatterns = { "/despesas", "/nova-despesa", "/editar-despesa", "/deletar-despesa"})
public class ExpensesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().contains("despesas")) {
			this.doGetExpenses(request, response);
		} else if (request.getServletPath().contains("nova-despesa")) {
			this.doGetNewExpense(request, response);
		} else if (request.getServletPath().contains("editar-despesa")) {
			this.doGetEditExpense(request, response);
		} else if (request.getServletPath().contains("deletar-despesa")) {
			this.doGetDropExpense(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().contains("nova-despesa")) {
			this.doPostNewExpense(request, response);
		} else if (request.getServletPath().contains("editar-despesa")) {
			this.doPostEditExpense(request, response);
		}
	}
// ============================================== doGet Methods ================================================//

	private void doGetExpenses(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Expense> expenses = PersistenceFactory.getPersistenceExpenseInstance().findAll();
		request.setAttribute("expenses", expenses);
		request.getRequestDispatcher("WEB-INF/views/expenses/expenses.jsp").forward(request, response);
	}

	private void doGetNewExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Expense expense = new Expense();
		request.setAttribute("action", "nova-despesa");
		request.setAttribute("expense", expense);
		request.getRequestDispatcher("WEB-INF/views/expenses/expense-new.jsp").forward(request, response);
	}

	private void doGetEditExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("id") != null) {
			Expense expense = PersistenceFactory.getPersistenceExpenseInstance()
					.find(Long.parseLong(request.getParameter("id")));
			request.setAttribute("expense", expense);
			request.setAttribute("action", "editar-despesa");
			request.getRequestDispatcher("WEB-INF/views/expenses/expense-edit.jsp").forward(request, response);
		} else {
			JOptionPane.showMessageDialog(null, "Despesa não informada");
		}
	}

	private void doGetDropExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersistenceExpense persistence = PersistenceFactory.getPersistenceExpenseInstance();
		Expense expense = persistence.find(Long.parseLong(request.getParameter("id")));

		if (expense.isValid() && expense.getId() != null) {
			try {
				persistence.delete(expense);
				JOptionPane.showMessageDialog(null, "Despesa deletada com sucesso");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao deletar Despesa");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Despesa invalida");
		}
		response.sendRedirect("despesas");
	}

// ============================================== doPost Methods ================================================//

	private void doPostNewExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Expense expense = new Expense(null, request.getParameter("expDescription"),
											Double.parseDouble(request.getParameter("expValuePay")),
											Format.formatStringToDate(request.getParameter("expDatePay")),
											request.getParameter("expPaid") == null ? "F" : "T");

		if (expense.isValid()) {
			PersistenceExpense persistence = PersistenceFactory.getPersistenceExpenseInstance();
			try {
				persistence.save(expense);
				JOptionPane.showMessageDialog(null, "Despesa salva com sucesso.");
				HttpSession session = request.getSession();
				session.removeAttribute("expense");
				response.sendRedirect("despesas");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao salvar Despesa. " + e);
				HttpSession session = request.getSession();
				session.setAttribute("nova-despesa", expense);
				response.sendRedirect("nova-despesa");
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("nova-despesa", expense);
			response.sendRedirect("nova-despesa");
		}
	}

	private void doPostEditExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersistenceExpense persistence = PersistenceFactory.getPersistenceExpenseInstance();
		Expense expense = persistence.find(Long.parseLong(request.getParameter("expId")));

		expense.setDescription(request.getParameter("expDescription"));
		expense.setValuePay(Double.parseDouble(request.getParameter("expValuePay")));
		expense.setDatePay(Format.formatStringToDate(request.getParameter("expDatePay")));
		expense.setPaid(request.getParameter("expPaid") == null ? "F" : "T");

		if (expense.isValid()) {
			try {
				persistence.update(expense);
				JOptionPane.showMessageDialog(null, "Despesa Alterada com sucesso.");
				HttpSession session = request.getSession();
				session.removeAttribute("expense");
				response.sendRedirect("despesas");
			} catch (Exception e) {
				HttpSession session = request.getSession();
				session.setAttribute("expense", expense);
				JOptionPane.showMessageDialog(null, "Erro ao salvar Despesa.");
				response.sendRedirect("editar-despesa");
				throw e;
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("expense", expense);
			response.sendRedirect("WEB-INF/views/expenses/expense-new.jsp");
		}

	}
}
