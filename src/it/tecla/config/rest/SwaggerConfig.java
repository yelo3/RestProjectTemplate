package it.tecla.config.rest;

import io.swagger.jaxrs.config.BeanConfig;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SwaggerConfig extends HttpServlet implements ServletContextListener {

	private static final long serialVersionUID = 1L;
	
	private static String contextPath;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		contextPath = sce.getServletContext().getContextPath();
		
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0");
		beanConfig.setBasePath(contextPath + "/api");
		// TODO ottimizzare
		beanConfig.setResourcePackage("io.swagger.resources,it.tecla");
		beanConfig.setScan(true);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String swaggerUiVersion = this.getInitParameter("swagger-ui.version");
		response.sendRedirect(contextPath + "/webjars/swagger-ui/" + swaggerUiVersion + "/?url=" + URLEncoder.encode(contextPath + "/api/swagger.json", "UTF-8"));
		
	}

}
