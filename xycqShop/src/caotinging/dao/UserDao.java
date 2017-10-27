package caotinging.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

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

}
