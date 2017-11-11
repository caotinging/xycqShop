package caotinging.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import caotinging.domain.Customer;
import caotinging.service.CustomerService;
import caotinging.service.impl.CustomerServiceImpl;

public class CustomerAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService service = new CustomerServiceImpl();

	/**
	 * @return
	 */
	public String list() {
		String custName = ServletActionContext.getRequest().getParameter("custName");
		List<Customer> list = null;
		
		if(StringUtils.isNotBlank(custName)) {
			list = service.findCustomerByName(custName);
			ServletActionContext.getRequest().setAttribute("list", list);
			return SUCCESS;
		}
		list = service.findAllCustomer();
		ServletActionContext.getRequest().setAttribute("list", list);
		return SUCCESS;
	}
}