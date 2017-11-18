package caotinging.test.springJdbc;

import java.util.List;

public interface UserDao {
	
	public void save(User u);
	
	public void update(User u);
	
	public void delete(Integer id);
	
	public User findById(Integer id);
	
	public List<User> findAll();
}
