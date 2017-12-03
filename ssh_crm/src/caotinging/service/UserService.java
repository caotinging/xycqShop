package caotinging.service;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.beans.PageBean;
import caotinging.domain.User;

public interface UserService {
	
	/**
	 * 根据用户名和密码获取user
	 * @param u
	 * @return
	 */
	public User getUserByCodePassword(User u);

	/**
	 * 保存用户
	 * @param user
	 */
	public void saveUser(User user);

	public PageBean<User> getUserList(DetachedCriteria criteria, Integer curr_Page, Integer page_Count);
}
