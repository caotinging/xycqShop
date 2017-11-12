package caotinging.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.domain.Customer;
import caotinging.service.CustomerService;
import caotinging.service.impl.CustomerServiceImpl;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	private static final long serialVersionUID = 1L;
	private CustomerService service = new CustomerServiceImpl();
	private Customer customer = new Customer();

	/**
	 * 执行添加客户操作后转发到客户列表
	 * @return 
	 */
	public String add() {
		service.addCustomer(customer);
		return "toList";
	}
	
	/**
	 * @return 筛选后客户信息
	 */
	public String list() {
		String custName = ServletActionContext.getRequest().getParameter("custName");
		List<Customer> list = null;
		
		if(StringUtils.isNotBlank(custName)) {
			list = service.findCustomerByName(custName);
		}else {
			list = service.findAllCustomer();
		}
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}

	@Override
	public Customer getModel() {
		return customer ;
	}
}