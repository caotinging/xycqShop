package caotinging.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.beans.PageBean;
import caotinging.domain.User;
import caotinging.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private UserService userService;
	private Integer curr_Page;
	private Integer page_Count;

	public Integer getCurr_Page() {
		return curr_Page;
	}

	public void setCurr_Page(Integer curr_Page) {
		this.curr_Page = curr_Page;
	}

	public Integer getPage_Count() {
		return page_Count;
	}

	public void setPage_Count(Integer page_Count) {
		this.page_Count = page_Count;
	}

	/**
	 * 获取用户列表
	 * @return
	 */
	public String userList() {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		PageBean<User> pageBean = userService.getUserList(criteria, curr_Page, page_Count);
		ActionContext.getContext().put("pageBean", pageBean);
		return "userList";
	}
	
	/**
	 * 用户注册功能
	 * @return
	 */
	public String regist() {
		if(user != null) {
			try {
				userService.saveUser(user);
			} catch (Exception e) {
				ActionContext.getContext().put("error", e.getMessage());
				return "regist";
			}
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
