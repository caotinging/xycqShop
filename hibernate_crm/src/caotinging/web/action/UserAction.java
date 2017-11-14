package caotinging.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.domain.User;
import caotinging.service.UserService;
import caotinging.service.impl.UserServiceImpl;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService service= new UserServiceImpl();
	
	public String login() throws Exception {
		User u = service.login(user);
		ActionContext.getContext().getSession().put("user", u);
		return "toIndex";
	}
	private static final long serialVersionUID = 1L;

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
