package caotinging.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import caotinging.utils.HibernateUtils;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtils.closeFactory();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	}

}
