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
import br.edu.utfpr.util.FlashMessage;
import br.edu.utfpr.util.Format;
import br.edu.utfpr.util.PersistenceFactory;

@WebServlet(urlPatterns = { "/despesas", "/nova-despesa", "/editar-despesa", "/deletar-despesa"})
public class ExpensesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		FlashMessage.clean();
		
		if(session.getAttribute("isLogged") != null) {
			if (request.getServletPath().contains("despesas")) {
				this.doGetExpenses(request, response);
			} else if (request.getServletPath().contains("nova-despesa")) {
				this.doGetNewExpense(request, response);
			} else if (request.getServletPath().contains("editar-despesa")) {
				this.doGetEditExpense(request, response);
			} else if (request.getServletPath().contains("deletar-despesa")) {
				this.doGetDropExpense(request, response);
			}
		}else {
			request.setAttribute("accessDenied", "true");
			request.getRequestDispatcher("login").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		FlashMessage.clean();
		
		if(session.getAttribute("isLogged") != null) {
			if (request.getServletPath().contains("nova-despesa")) {
				this.doPostNewExpense(request, response);
			} else if (request.getServletPath().contains("editar-despesa")) {
				this.doPostEditExpense(request, response);
			}
		}else {
			request.setAttribute("accessDenied", true);
			request.getRequestDispatcher("login").forward(request, response);
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
			FlashMessage.addMessage("danger", "Despesa não informada.");
			request.setAttribute("flash.messages", FlashMessage.getMessages());
			response.sendRedirect("despesas");
		}
	}

	private void doGetDropExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersistenceExpense persistence = PersistenceFactory.getPersistenceExpenseInstance();
		Expense expense = persistence.find(Long.parseLong(request.getParameter("id")));

		if (expense.isValid() && expense.getId() != null) {
			try {
				persistence.delete(expense);
				FlashMessage.addMessage("success", "Despesa deletada com sucesso.");
			} catch (Exception e) {
				FlashMessage.addMessage("danger", "Erro ao deletar Despesa.");
			}
		} else {
			FlashMessage.addMessage("danger", "Despesa inválida.");
		}
		request.setAttribute("flash.messages", FlashMessage.getMessages());
		response.sendRedirect("despesas");
	}

// ============================================== doPost Methods ================================================//

	private void doPostNewExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Expense expense = new Expense(null, request.getParameter("expDescription"),
											Double.parseDouble(request.getParameter("expValuePay")),
											Format.formatStringToDate(request.getParameter("expDatePay")),
											request.getParameter("expPaid") == null ? "F" : "T");

		if (expense.isValid()) {
			PersistenceExpense persistence = PersistenceFactory.getPersistenceExpenseInstance();
			try {
				persistence.save(expense);
				FlashMessage.addMessage("success", "Despesa salva com sucesso.");
				request.setAttribute("flash.messages", FlashMessage.getMessages());
				
				session.removeAttribute("expense");
				response.sendRedirect("despesas");
				return;
			} catch (Exception e) {
				FlashMessage.addMessage("danger", "Erro ao salvar Despesa."+e);
			}
		} else {
			FlashMessage.addMessage("danger", "Despesa não informada ou não localizada.");
		}
		
		request.setAttribute("flash.messages", FlashMessage.getMessages());
		session.setAttribute("nova-despesa", expense);
		response.sendRedirect("nova-despesa");

	}

	private void doPostEditExpense(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PersistenceExpense persistence = PersistenceFactory.getPersistenceExpenseInstance();
		Expense expense = persistence.find(Long.parseLong(request.getParameter("expId")));

		expense.setDescription(request.getParameter("expDescription"));
		expense.setValuePay(Double.parseDouble(request.getParameter("expValuePay")));
		expense.setDatePay(Format.formatStringToDate(request.getParameter("expDatePay")));
		expense.setPaid(request.getParameter("expPaid") == null ? "F" : "T");

		if (expense.isValid()) {
			try {
				persistence.update(expense);
				
				FlashMessage.addMessage("success", "Despesa Alterada com sucesso.");
				request.setAttribute("flash.messages", FlashMessage.getMessages());
				session.removeAttribute("expense");
				response.sendRedirect("despesas");
				return;
			} catch (Exception e) {
				FlashMessage.addMessage("danger", "Erro ao salvar Despesa: "+e);
			}
		} else {
			FlashMessage.addMessage("danger", "Erro ao salvar Despesa, Verifique os camos indicados.");
		}

		request.setAttribute("flash.messages", FlashMessage.getMessages());
		session.setAttribute("expense", expense);
		response.sendRedirect("editar-despesa");
	}
}
