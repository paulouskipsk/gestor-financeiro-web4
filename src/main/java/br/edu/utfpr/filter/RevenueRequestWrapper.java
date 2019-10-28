package br.edu.utfpr.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RevenueRequestWrapper extends HttpServletRequestWrapper{

	public RevenueRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		
		if(parameter.equals("revValueReceiveble")) {
			value = value.replaceAll(",", ".");
		}else if(parameter.equals("revDateReceiveble")) {
			String[] aux = value.split("-");
			value = aux[2]+"/"+aux[1]+"/"+aux[0];
		}else if(parameter.equals("revDescription")) {
			value = value.toUpperCase();
		}
		
		return value;
	}
}
