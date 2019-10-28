package br.edu.utfpr.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ExpenseRequestWrapper extends HttpServletRequestWrapper{

	public ExpenseRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		
		if(parameter.equals("expValuePay")) {
			value = value.replaceAll(",", ".");
		}else if(parameter.equals("expDatePay")) {
			String[] aux = value.split("-");
			value = aux[2]+"/"+aux[1]+"/"+aux[0];
		}else if(parameter.equals("expDescription")) {
			value = value.toUpperCase();
		}
		
		return value;
	}
}
