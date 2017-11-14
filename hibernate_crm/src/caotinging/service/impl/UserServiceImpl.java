package caotinging.service.impl;

import org.hibernate.Transaction;

import caotinging.dao.UserDao;
import caotinging.dao.impl.UserDaoImpl;
import caotinging.domain.User;
import caotinging.service.UserService;
import caotinging.utils.HibernateUtils;

public class UserServiceImpl implements UserService {

	private UserDao dao = new UserDaoImpl();

	@Override
	public User login(User user) {
		Transaction transaction = HibernateUtils.startTransaction();
		
		if(user == null) {
			System.out.println("user==null");
			throw new RuntimeException("系统错误！");
		}
		User u = dao.findUserByName(user.getUser_code());
		transaction.commit();
		
		if(u == null) {
			throw new RuntimeException("用户名不存在！");
		}
		if(!user.getUser_password().equals(u.getUser_password())) {
			throw new RuntimeException("密码错误！");
		}
		return u;
	}

}
