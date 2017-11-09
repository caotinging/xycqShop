package caotinging.web.servlet;

import caotinging.domain.Customer;
import caotinging.service.CustomerService;
import caotinging.service.impl.CustomerServiceImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

public class CustomerServlet extends BaseServlet {
	private CustomerService service = new CustomerServiceImpl();
	private static final long serialVersionUID = 1L;
	
	/**
	 * 根据名字对客户进行模糊查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findCustomerByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custName = request.getParameter("custName");
		if(custName != null) {
			List<Customer> list = service.findCustomerByName(custName);
			request.setAttribute("list", list);
			request.setAttribute("custName", custName);
			request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
		}
	}
	
	/**
	 * 获取所有客户的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService service = new CustomerServiceImpl();
		List<Customer> list = service.findAllCustomer();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
	}

	/**
	 * 添加新客户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();

		Map<String, String[]> parameterMap = request.getParameterMap();
		try {
			BeanUtils.populate(customer, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		this.service.addCustomer(customer);
		response.sendRedirect(request.getContextPath() + "/ListCustomerServlet");
	}
}
