package br.edu.utfpr.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.utfpr.util.Constants;

@WebServlet("/regras-de-usuarios")
public class ReportsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().contains("regras-de-usuarios")) {			
			Gson gsonObj = new GsonBuilder().create(); 
			String gsonRoles = gsonObj.toJson(Constants.RoleAccess.getRolesAccess());
			
			response.getWriter().print(gsonRoles);
		}
	}
}
