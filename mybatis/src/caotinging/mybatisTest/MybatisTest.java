package caotinging.mybatisTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import caotinging.pojo.User;

public class MybatisTest {
	
	/**
	 * 测试mybatis主键自增返回主键的值
	 * Integer自增是数据保存完后再生成主键因此配置文件中属性是after
	 * 如果是uuid，则是想生成随机主键，再一起保存数据，因此用before
	 * @throws Exception
	 */
	@Test
	public void mybatisTest6() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = factory.openSession();
		
		User user = new User();
		user.setAddress("hhsjkl");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setUsername("hehehe");
		
		session.insert("addUserGetId", user);
		session.commit();
		session.close();
		System.out.println(user.getId());
		
	}
	
	/**
	 * 测试删除指定用户
	 * @throws Exception
	 */
	@Test
	public void mybatisTest5() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = factory.openSession();
		session.delete("deleteUserById", 27);
		
		session.commit();
		session.close();
	}
	
	/**
	 * 测试更新用户信息
	 * @throws Exception
	 */
	@Test
	public void mybatisTest4() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = factory.openSession();
		
		User user = new User();
		user.setAddress("hhsjkl");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setUsername("hehe");
		user.setId(27);
		
		session.update("updateUserAll", user);
		session.commit();
		session.close();
	}

	/**
	 * 测试根据用户名模糊查询用户信息
	 * @throws Exception 
	 */
	@Test
	public void mybatisTest3() throws Exception {
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession session = factory.openSession();
		List<User> userList = session.selectList("findUSerByUsername", "王");
		
		for (User user : userList) {
			
			System.out.println(user);
		}
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
