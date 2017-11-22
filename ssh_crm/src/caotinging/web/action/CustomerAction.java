package caotinging.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.beans.PageBean;
import caotinging.domain.Customer;
import caotinging.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	private static final long serialVersionUID = 1L;
	private Customer customer = new Customer();
	private Integer currentPage;
	private Integer pageCount;
	private CustomerService customerService;

	@Resource(name="customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 获取分页显示customer数据
	 * @return 
	 */
	public String custList() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//这一步检验非常重要，如果不进行校验当提交的cust_name为空时查询结果就是%null%
		if(StringUtils.isNotBlank(customer.getCust_name())) {
			criteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		PageBean<Customer> pageBean = customerService.getPageBean(criteria, currentPage, pageCount);
		
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "toCustList";
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public Customer getModel() {
		return customer;
	}

}
