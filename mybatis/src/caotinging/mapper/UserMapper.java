package caotinging.mapper;

import java.util.List;

import caotinging.pojo.Queryvo;
import caotinging.pojo.User;

public interface UserMapper {

	/**
	 * Mapper接口开发需要遵循以下规范： 1、Mapper.xml文件中的namespace与mapper接口的类路径相同。
	 * 2、Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
	 * 3、Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
	 * 4、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
	 */
	
	/**
	 * 根据用户名和性别模糊查询用户优化
	 * @param user
	 * @return
	 */
	public List<User> findUserByNameAndSex(User user);
	
	/**
	 * 根据姓名和性别查询用户
	 * @return
	 */
	public List<User> getUserbyNameAndSex(User user);
	
	/**
	 * 查询用户数量
	 * @return
	 */
	public Integer getUserCount();
	
	/**
	 * 通过pojo封装pojo对象完成映射
	 * @param vo
	 * @return
	 */
	public List<User> findUserByQueryVo(Queryvo vo);
	
	/**
	 * 根据id1获取用户信息
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);
	
	/**
	 * 根据用户名模糊查询用户信息
	 * @param username
	 * @return
	 */
	public List<User> findUSerByUsername(String username);
	
	/**
	 * 新增一个用户并且返回他的主键值
	 * @param user
	 * @return
	 */
	public Integer addUserGetId(User user); 
}
