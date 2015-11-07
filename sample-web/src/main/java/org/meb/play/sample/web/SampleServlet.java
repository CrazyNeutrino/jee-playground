package org.meb.play.sample.web;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meb.play.sample.ejb.SampleService;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/")
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InitialContext initialContext;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Hashtable<Object, Object> env = new Hashtable<>();
//			env.put(Context.INITIAL_CONTEXT_FACTORY,
//					"com.sun.enterprise.naming.SerialInitContextFactory");
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.openejb.client.LocalInitialContextFactory");
			env.put(Context.PROVIDER_URL, "localhost:3700");
			initialContext = new InitialContext();
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SampleService ss = (SampleService) initialContext
					.lookup("java:global/sample/sample-ejb/SampleServiceBean/SampleService");
			response.getWriter().append("Counter: ").append(Integer.toString(ss.getCounter()));
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
