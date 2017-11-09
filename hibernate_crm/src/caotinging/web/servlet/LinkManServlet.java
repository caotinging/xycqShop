package caotinging.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;
import caotinging.service.impl.LinkManServiceImpl;

public class LinkManServlet extends BaseServlet {
	private LinkManService service = new LinkManServiceImpl();
	
	/**
	 * 通过名字模糊查询联系人
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findLinkManByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lkmName = request.getParameter("lkmName");
		
		if(lkmName != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
			criteria.add(Restrictions.like("lkm_name", "%"+lkmName+"%"));
			
			List<LinkMan> list = service.findLinkManByName(criteria);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/jsp/linkman/list.jsp").forward(request, response);
		}
		
	}
	
	/**
	 * 查找所有联系人
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllLinkMan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LinkMan> list = service.findAllLinkMan();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/linkman/list.jsp").forward(request, response);
	}
	
	/**
	 * 新增联系人
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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
