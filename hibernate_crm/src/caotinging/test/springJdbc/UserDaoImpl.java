package caotinging.test.springJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation=Isolation.REPEATABLE_READ, readOnly=false, propagation=Propagation.REQUIRED)
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
//	private JdbcTemplate jt;
	
	@Override
	public void save(User u) {
		String sql = "insert into t_user values(null, ?)";
		super.getJdbcTemplate().update(sql, u.getName());
//		int i = 1/0;
	}

	@Override
	public void update(User u) {
		String sql = "update t_user set name=? where id=?";
		super.getJdbcTemplate().update(sql, u.getName(), u.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from t_user where id=?";
		super.getJdbcTemplate().update(sql, id);
	}

	@Override
	public User findById(Integer id) {
		String sql="select * from t_user where id=?";
		User user = super.getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				return u;
			}
		}, id);
		
		return user;
	}

	@Override
	public List<User> findAll() {
		String sql = "select * from t_user";
		List<User> list = super.getJdbcTemplate().query(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				return u;
			}});
		return list;
	}
	
}
