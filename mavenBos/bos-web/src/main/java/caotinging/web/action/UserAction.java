package caotinging.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.User;
import caotinging.service.IUserService;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;

	private User user = super.get();
	private String checkCode;
	private IUserService userService;

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注销登录
	 * @return
	 */
	public String loginOut() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "tologin";
	}
	
	/**
	 * 用户登录功能的实现
	 * @return
	 */
	public String login() {
		if(checkCode == null) {
			this.addActionError("请输入验证码！");
			return LOGIN;
		}
		
		if(user != null) {
			//检验验证码
			String checkN = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
			if(!StringUtils.isNotBlank(checkN) || !checkCode.equals(checkN)) {
				this.addActionError("验证码输入错误！");
				return LOGIN;
			}
			
			//验证码输入正确校验用户
			User existU = userService.checkUser(user);
			
			if(existU == null) {
				this.addActionError("用户名或密码输入错误！");
				return LOGIN;
			}
			ServletActionContext.getRequest().getSession().setAttribute("user", existU);
			return HOME;
		}else {
			this.addActionError("请输入用户名和密码！");
			return LOGIN;
		}
	}
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	public String getCheckCode() {
		return checkCode;
	}

}
