package caotinging.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import caotinging.domain.User;
import caotinging.utils.DataSourceUtils;

public class UserDao {

	private QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	
	/**
	 * 将用户的注册信息存入数据库
	 * @param user 用户注册信息
	 * @return 执行结果
	 * @throws SQLException 
	 */
	public int register(User user) throws SQLException {
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return update;
	}

	/**
	 * 激活账户
	 * @param code 用户激活码
	 * @return 执行结果
	 * @throws SQLException 
	 */
	public int setState(String code) throws SQLException {
		String sql = "update user set state=? where code=?";
		int update = qr.update(sql, 1, code);
		return update;
	}

	/**
	 * 从数据库中查询用户名是否存在
	 * @param username 用户名
	 * @return 查询结果
	 * @throws SQLException 
	 */
	public Long checkUserName(String username) throws SQLException {
		String sql = "select count(*) from user where username=?";
		Long query = (Long) qr.query(sql, new ScalarHandler(), username);
		return query;
	}

}
