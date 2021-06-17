package br.czar.odonto.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.mail.iap.Response;

import br.czar.odonto.aplication.storage.Request;
import br.czar.odonto.model.Dentist;
import br.czar.odonto.model.Patient;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/admin/*","/auth/*"})
public class SecurityFilter implements Filter {
	public static final String LOGGED_USER = "logged-user";

	@Override
	public void doFilter(
			ServletRequest req,
			ServletResponse res,
			FilterChain chain
	) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) req;
		HttpServletResponse servletResponse = (HttpServletResponse) res;

		servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		servletResponse.setHeader("Pragma", "no-cache"); 
		servletResponse.setDateHeader("Expires", 0); 
		
		HttpSession session = servletRequest.getSession(false);
		String endereco = servletRequest.getRequestURI();
		System.out.println(endereco);
		
		Object user = (session != null)
			?session.getAttribute(LOGGED_USER)
			:null;
		
		if (user == null)
			servletResponse.sendRedirect("/OdontoClinica/login.xhtml");
		else if (endereco.contains("/auth/")) {
			if (user instanceof Patient) {
				chain.doFilter(req,res);
			}else {
				servletResponse.sendRedirect("/OdontoClinica/index.xhtml");
			}
		}
		else if (user instanceof Dentist)
			chain.doFilter(req, res);
		else
			servletResponse.sendRedirect("/OdontoClinica/index.xhtml");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Admin Filter Iniciado.");
		Filter.super.init(filterConfig);
	}

}
