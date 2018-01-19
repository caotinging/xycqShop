package caotinging.mybatisTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import caotinging.pojo.User;

public class MybatisTest {

	/**
	 * 测试根据用户名模糊查询用户信息
	 */
	@Test
	public void mybatisTest3() {
		
	}
	
	/**
	 * 测试新增一个用户
	 * @throws Exception
	 */
	@Test
	public void mybatisTest2() throws Exception {
		File file = Resources.getResourceAsFile("SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(new FileInputStream(file));
		
		SqlSession session = factory.openSession();
		User user = new User();
		user.setAddress("hhsjkl");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setUsername("haha");
		session.insert("addUser", user );
		session.commit();
		session.close();
	}
	
	/**
	 * 测试根据用户id获取用户信息
	 * @throws Exception
	 */
	@Test
	public void mybatisTest1() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		//获取到mybatis的session工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = factory.openSession();
		User user = session.selectOne("findUserById", 10);
		
		System.out.println(user);
		session.close();
	}

}
