package caotinging.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {

	private static DataSource dataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	/**
	 * 获取一个数据库连接池
	 * @return 数据库连接池
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 获得一个新的连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * 获取连接对象
	 * @return 数据库连接对象
	 * @throws SQLException
	 */
	public static Connection getCurrentConnection() throws SQLException {

		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}

	/**
	 * 开启事务
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.setAutoCommit(false);
		}
	}

	/**
	 * 事务回滚
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.rollback();
		}
	}

	/**
	 * 提交并且 关闭资源及从ThreadLocall中释放
	 * @throws SQLException
	 */
	public static void commitAndRelease() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.commit(); // 事务提交
			con.close();// 关闭资源
			tl.remove();// 从线程绑定中移除
		}
	}

	/**
	 * 关闭资源
	 * @throws SQLException
	 */
	public static void closeConnection() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.close();
		}
	}

	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

}
