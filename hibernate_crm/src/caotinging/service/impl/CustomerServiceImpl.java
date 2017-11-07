package caotinging.service.impl;

import caotinging.dao.CustomerDao;
import caotinging.dao.impl.CustomerDaoImpl;
import caotinging.domain.Customer;
import caotinging.service.CustomerService;

public class CustomerServiceImpl
  implements CustomerService
{
  private CustomerDao dao = new CustomerDaoImpl();
  
  public void addCustomer(Customer customer)
  {
    this.dao.addCustomer(customer);
  }
}
