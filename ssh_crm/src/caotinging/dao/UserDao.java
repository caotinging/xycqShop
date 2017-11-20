package caotinging.dao;

import caotinging.domain.User;

public interface UserDao {
	
	public User getUserByCode(String user_code);
}
