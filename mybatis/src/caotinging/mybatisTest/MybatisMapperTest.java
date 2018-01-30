package caotinging.mybatisTest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import caotinging.mapper.OrderMapper;
import caotinging.mapper.UserMapper;
import caotinging.pojo.Order;
import caotinging.pojo.OrderUser;
import caotinging.pojo.Queryvo;
import caotinging.pojo.User;

public class MybatisMapperTest {

	private SqlSessionFactory factory;
	
	@Before
	public void init() throws Exception {
		InputStream stream = Resources.getResourceAsStream("SqlMapConfig.xml");
		factory = new SqlSessionFactoryBuilder().build(stream);
	}
	
	/**
	 * 测试查询用户信息以及用户对应的订单信息
	 */
	@Test
	public void mapperTest10() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		List<User> list = mapper.findUserOrder();
		for (User user : list) {
			System.out.println(user);
		}
		session.close();
	}
	
	/**
	 * 测试利用resultMap获取订单以及对应的用户信息
	 */
	@Test
	public void mapperTest9() {
		SqlSession session = factory.openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		
		List<Order> list = mapper.findAllOrderUser();
		for (Order order : list) {
			System.out.println(order);
		}
		session.close();
	}
	
	/**
	 * 测试查询订单并获取相应的用户信息
	 */
	@Test
	public void mapperTest8() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		List<OrderUser> list = mapper.findOrderUser();
		for (OrderUser orderUser : list) {
			System.out.println(orderUser);
		}
		session.close();
	}
	
	/**
	 * 根据多个id查询用户
	 */
	@Test
	public void mapperTest7() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		Queryvo vo = new Queryvo();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(10);
		ids.add(3);
		vo.setIds(ids);
		
		List<User> list = mapper.findUsersByIds(vo);
		for (User user : list) {
			System.out.println(user);
		}
		session.close();
	}
	
	/**
	 * 测试根据用户名和性别模糊查询的优化方法
	 */
	@Test
	public void mapperTest6() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		User user = new User();
		user.setUsername("张");
		
		List<User> list = mapper.findUserByNameAndSex(user);
		for (User user2 : list) {
			System.out.println(user2);
		}
	}
	
	/**
	 * 测试根据用户名和性别查询用户
	 */
	@Test
	public void mapperTest5() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		User user = new User();
		user.setUsername("张");
		user.setSex("1");
		
		List<User> list = mapper.getUserbyNameAndSex(user);
		for (User user2 : list) {
			System.out.println(user2);
		}
	}
	
	/**
	 * 查询全部的订单信息
	 */
	@Test
	public void mapperTest4() {
		SqlSession session = factory.openSession();
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		
		List<Order> list = mapper.findAllOrder();
		for (Order order : list) {
			System.out.println(order);
		}
		
	}
	
	@Test
	/**
	 * 查询用户表的所有数据的条数
	 */
	public void mapperTest3(){
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		Integer count = mapper.getUserCount();
		System.out.println(count);
		
		session.close();
	}
	
	/**
	 * 通过QueryVo封装的user对象进行用户名模糊查询
	 */
	@Test
	public void mapperTest2() {
		Queryvo vo = new Queryvo();
		User user = new User();
		user.setUsername("张");
		vo.setUser(user);
		
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		List<User> list = mapper.findUserByQueryVo(vo);
		for (User user2 : list) {
			System.out.println(user2.getUsername());
		}
	}
	
	/**
	 * 测试mapper接口中的方法
	 */
	@Test
	public void mapperTest() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		User user = mapper.findUserById(29);
		System.out.println(user);
		
		List<User> list = mapper.findUSerByUsername("王");
		System.out.println("--------------------------");
		for (User user2 : list) {
			System.out.println(user2);
		}
		
		User user3 = new User();
		user3.setUsername("caotinging");
		
		Integer id = mapper.addUserGetId(user3);
		System.out.println(id);
		
		session.commit();
		session.close();
	}
}
