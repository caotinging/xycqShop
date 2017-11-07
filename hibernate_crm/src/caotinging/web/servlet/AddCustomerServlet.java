package caotinging.web.servlet;

import caotinging.domain.Customer;
import caotinging.service.CustomerService;
import caotinging.service.impl.CustomerServiceImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

public class AddCustomerServlet extends HttpServlet {
	private CustomerService service = new CustomerServiceImpl();
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
