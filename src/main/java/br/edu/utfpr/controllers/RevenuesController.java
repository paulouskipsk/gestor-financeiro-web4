package br.edu.utfpr.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.utfpr.model.Revenue;
import br.edu.utfpr.persistence.PersistenceRevenue;
import br.edu.utfpr.util.FlashMessage;
import br.edu.utfpr.util.Format;
import br.edu.utfpr.util.PersistenceFactory;

@WebServlet(urlPatterns = { "/receitas", "/nova-receita", "/editar-receita", "/deletar-receita" })
public class RevenuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		FlashMessage.clean();
		
		if(session.getAttribute("isLogged") != null) {
			if (request.getServletPath().contains("receitas")) {
				this.doGetRevenues(request, response);
			} else if (request.getServletPath().contains("nova-receita")) {
				this.doGetNewRevenue(request, response);
			} else if (request.getServletPath().contains("editar-receita")) {
				this.doGetEditRevenue(request, response);
			} else if (request.getServletPath().contains("deletar-receita")) {
				this.doGetDropRevenue(request, response);
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
			if (request.getServletPath().contains("nova-receita")) {
				this.doPostNewRevenue(request, response);
			} else if (request.getServletPath().contains("editar-receita")) {
				this.doPostEditRevenue(request, response);
			}
		}else {
			request.setAttribute("accessDenied", "true");
			request.getRequestDispatcher("login").forward(request, response);
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
			FlashMessage.addMessage("danger", "Receita não informada ou não encontrada.");
			request.setAttribute("flash.messages", FlashMessage.getMessages());
			response.sendRedirect("receitas");
		}	
	}

	private void doGetDropRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersistenceRevenue persistence = PersistenceFactory.getPersistenceRevenueInstance();
		Revenue revenue = persistence.find(Long.parseLong(request.getParameter("id")));

		if (revenue.isValid() && revenue.getId() != null) {
			try {
				persistence.delete(revenue);
				FlashMessage.addMessage("success", "Receita deletada com sucesso.");
			} catch (Exception e) {
				FlashMessage.addMessage("danger", "Erro ao deletar Receita."+e);
			}
		} else {
			FlashMessage.addMessage("danger", "Receita Inválida.");
		}
		
		request.setAttribute("flash.messages", FlashMessage.getMessages());
		response.sendRedirect("receitas");
	}

// ============================================== doPost Methods ================================================//

	private void doPostNewRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Revenue revenue = new Revenue(  null, 
										request.getParameter("revDescription"),
										Double.parseDouble(request.getParameter("revValueReceiveble")),
										Format.formatStringToDate(request.getParameter("revDateReceiveble")),
										request.getParameter("revReceivebled") == null ? "F" : "T");

		if (revenue.isValid()) {
			PersistenceRevenue persistence = PersistenceFactory.getPersistenceRevenueInstance();
			try {
				persistence.save(revenue);
				
				FlashMessage.addMessage("success", "Receita salva com sucesso.");
				request.setAttribute("flash.messages", FlashMessage.getMessages());
				session.removeAttribute("revenue");
				response.sendRedirect("receitas");
				return;
			} catch (Exception e) {
				FlashMessage.addMessage("danger", "Erro ao salvar Receita. " + e);
			}
		} else {
			FlashMessage.addMessage("danger", "Receita não informada ou não encontrada.");
		}
		
		request.setAttribute("flash.messages", FlashMessage.getMessages());
		session.setAttribute("nova-receita", revenue);
		response.sendRedirect("nova-receita");
	}

	private void doPostEditRevenue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PersistenceRevenue persistence = PersistenceFactory.getPersistenceRevenueInstance();
		Revenue revenue = persistence.find(Long.parseLong(request.getParameter("revId")));

		revenue.setDescription(request.getParameter("revDescription"));
		revenue.setValueReceiveble(Double.parseDouble(request.getParameter("revValueReceiveble")));
		revenue.setDateReceiveble(Format.formatStringToDate(request.getParameter("revDateReceiveble")));
		revenue.setReceivebled(request.getParameter("revReceivebled") == null ? "F" : "T");

		if (revenue.isValid()) {
			try {
				persistence.update(revenue);
				
				FlashMessage.addMessage("success", "Receita Alterada com sucesso.");
				request.setAttribute("flash.messages", FlashMessage.getMessages());		
				session.removeAttribute("revenue");
				response.sendRedirect("receitas");
				return;
			} catch (Exception e) {
				FlashMessage.addMessage("danger", "Erro ao salvar Receita: "+e);
			}
		} else {
			FlashMessage.addMessage("danger", "Receita não informada ou não encontrada.");
		}
		
		request.setAttribute("flash.messages", FlashMessage.getMessages());
		session.setAttribute("revenue", revenue);
		response.sendRedirect("editar-receita");
	}
}
