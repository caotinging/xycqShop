
import java.util.List;

import caotinging.pojo.User;

public interface UserDao {

	/**
	 * 1.实现根据用户id查询 2.实现根据用户名模糊查询 3.添加用户
	 */
	public User findUserById(Integer id);
	
	public List<User> findUserByName(String username);
	
	public void saveUser(User user);
}
