package caotinging.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import caotinging.dao.IUserDao;
import caotinging.dao.base.impl.BaseDaoImpl;
import caotinging.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User getUserByNameAndPassword(String username, String md5) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", md5));
		
		List<User> list = findByCriteria(criteria);
		
		if(list != null && list.size() >0) {
			return list.get(0);
		}
		return null;
	}

}
