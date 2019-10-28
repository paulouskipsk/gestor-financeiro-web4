package br.edu.utfpr.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/nova-receita", "/editar-receita" })
public class RevenueFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		chain.doFilter(new RevenueRequestWrapper(((HttpServletRequest) request)), response);
	}
	
    public RevenueFilter() { }
	public void destroy() {	}
	public void init(FilterConfig fConfig) throws ServletException {}

}
