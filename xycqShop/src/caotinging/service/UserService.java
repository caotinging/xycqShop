package caotinging.service;

import java.sql.SQLException;

import caotinging.dao.UserDao;
import caotinging.domain.User;

public class UserService {

	private final UserDao dao = new UserDao();
	
	/**
	 * 存储用户注册信息
	 * @param user 用户信息
	 * @return 执行结果
	 */
	public boolean register(User user) {
		int res = 0;
		try {
			res = dao.register(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res>0?true:false;
	}

}
