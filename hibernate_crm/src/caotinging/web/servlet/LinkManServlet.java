package caotinging.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;
import caotinging.service.impl.LinkManServiceImpl;

public class LinkManServlet extends BaseServlet {
	private LinkManService service = new LinkManServiceImpl();
	
	public void addLinkMan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Long cust_id = Long.parseLong(request.getParameter("cust_id"));
		boolean isSuccess = false;
		
		if(cust_id != null && parameterMap != null) {
			LinkMan linkman = new LinkMan();
			try {
				BeanUtils.populate(linkman, parameterMap);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			isSuccess = service.addLinkMan(linkman, cust_id);
		}
		if(isSuccess) {
			response.sendRedirect(request.getContextPath()+"/jsp/linkman/list.jsp");
		}else{
			response.sendRedirect(request.getContextPath()+"/jsp/error.jsp");
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
