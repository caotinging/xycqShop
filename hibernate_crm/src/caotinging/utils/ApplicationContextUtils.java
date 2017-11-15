package caotinging.utils;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContextUtils {
	private static ServletContext sc;
	private static WebApplicationContext context;
	
	static{
		sc = ServletActionContext.getServletContext();
		context = WebApplicationContextUtils.getWebApplicationContext(sc);
	}
	
	public static WebApplicationContext getApplicationContext() {
		return context;
	}
}
