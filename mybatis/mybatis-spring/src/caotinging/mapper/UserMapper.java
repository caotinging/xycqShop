package caotinging.mapper;

import java.util.List;

import caotinging.pojo.User;

public interface UserMapper {

	public User findUserById(Integer id);
	
	public List<User> findUserByName(String name);
	
	public void saveUser(User user);
}
