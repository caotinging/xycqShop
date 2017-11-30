package caotinging.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.domain.User;
import caotinging.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserService userService;

	/**
	 * 用户注册功能
	 * @return
	 */
	public String regist() {
		if(user != null) {
			userService.saveUser(user);
		}else {
			return "error";
		}
		return "toLogin";
	}
	
	/**
	 * 用户登录校验
	 * @return
	 */
	public String userLogin() {
		User existU = userService.getUserByCodePassword(user);
		ActionContext.getContext().getSession().put("user", existU);
		
		return "toIndex";
	}

	@Override
	public User getModel() {
		return user ;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
