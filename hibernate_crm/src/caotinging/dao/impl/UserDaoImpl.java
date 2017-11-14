package caotinging.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caotinging.dao.UserDao;
import caotinging.domain.User;
import caotinging.utils.HibernateUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User findUserByName(String user_code) {
		Session session = HibernateUtils.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("user_code", user_code));
		
		User u = (User) criteria.uniqueResult();
		return u;
	}

}
