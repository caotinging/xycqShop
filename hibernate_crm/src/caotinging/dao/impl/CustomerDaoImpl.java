package caotinging.dao.impl;

import caotinging.dao.CustomerDao;
import caotinging.domain.Customer;
import caotinging.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDaoImpl
  implements CustomerDao
{
  public void addCustomer(Customer customer)
  {
    Session session = HibernateUtils.getSession();
    Transaction transaction = session.beginTransaction();
    
    session.save(customer);
    
    transaction.commit();
    session.close();
    HibernateUtils.closeFactory();
  }
}