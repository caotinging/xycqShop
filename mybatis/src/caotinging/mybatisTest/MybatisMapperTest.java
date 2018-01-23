package caotinging.mybatisTest;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import caotinging.mapper.UserMapper;
import caotinging.pojo.Queryvo;
import caotinging.pojo.User;

public class MybatisMapperTest {

	private SqlSessionFactory factory;
	
	@Before
	public void init() throws Exception {
		InputStream stream = Resources.getResourceAsStream("SqlMapConfig.xml");
		factory = new SqlSessionFactoryBuilder().build(stream);
	}
	
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
