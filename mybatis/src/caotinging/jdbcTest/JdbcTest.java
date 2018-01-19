package caotinging.jdbcTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import caotinging.pojo.User;

public class JdbcTest {

	@Test
	public void jdbcTest() {
		Properties prop = new Properties();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet query = null;
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("src/jdbc.properties"));

			prop.load(in);
			Class.forName(prop.getProperty("jdbc.driverClass"));

			conn = DriverManager.getConnection(prop.getProperty("jdbc.jdbcUrl"), prop.getProperty("jdbc.user"),
					prop.getProperty("jdbc.password"));

			String sql = "select * from user where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, 10);

			query = pstat.executeQuery();

			User user = new User();
			while (query.next()) {
				user.setId(query.getInt("id"));
				user.setSex(query.getString("sex"));
				user.setAddress(query.getString("address"));
				user.setBirthday(query.getDate("birthday"));
				user.setUsername(query.getString("username"));
				System.out.println(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pstat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				query.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
