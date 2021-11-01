package com.tfm.webapi.gesparcbackend.core.filter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(value = { "/*" }, description = "Permet d'ajouter une correlationId à toutes les réponses")
public class CorrelationIdFilter implements Filter {
	
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("X-GespBackend-CorrelationId", generate());
		
		chain.doFilter(request, response);
	}
	
	private static String generate() {
		final Random random = ThreadLocalRandom.current();
		return Long.toHexString(random.nextLong() | Long.MIN_VALUE);
	}

}
