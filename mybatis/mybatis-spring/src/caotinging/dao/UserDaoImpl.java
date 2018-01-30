package caotinging.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import caotinging.pojo.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public User findUserById(Integer id) {
		User user = super.getSqlSession().selectOne("findUserById", id);
		return user;
	}

	@Override
	public List<User> findUserByName(String username) {
		List<User> list = super.getSqlSession().selectList("findUserByName", username);
		return list;
	}

	@Override
	public void saveUser(User user) {
		super.getSqlSession().insert("saveUser", user);
	}

}
