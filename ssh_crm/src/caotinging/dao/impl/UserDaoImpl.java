package caotinging.dao.impl;

import java.util.List;

//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import caotinging.dao.UserDao;
import caotinging.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
	@Override
	public User getUserByCode(final String user_code) {
		//使用
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		criteria.add(Restrictions.eq("user_code", user_code));
		
		List<User> userList = super.findByCriteria(criteria);
		
		if(userList != null && userList.size() >0) {
			return userList.get(0);
		} else {
			return null;
		}
		
//		使用hql进行操作
		/*HibernateTemplate ht = super.getHibernateTemplate();
		User u = ht.execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session) throws HibernateException {
				String hql="from User where user_code=?";
				Query query = session.createQuery(hql);
				
				query.setParameter(0, user_code);
				
				User user = (User) query.uniqueResult();
				
				return user;
			}
		});
		return u;*/
	}

}
