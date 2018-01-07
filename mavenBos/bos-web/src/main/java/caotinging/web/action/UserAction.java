package caotinging.web.action;

import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.User;
import caotinging.service.IUserService;
import caotinging.utils.BosCommonUtils;
import caotinging.utils.MD5Utils;
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
	
	private String[] rolesId;

	/**
	 * 用户列表查询
	 * @return
	 */
	public String pageQuery() {
		try{
		userService.pageQuery(pageBean);
		java2JsonWrite(pageBean, new String[]{"currentPage","pageCount","criteria","roles","birthday"});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 新增用户
	 * @return
	 */
	public String addUser() {
		userService.addUser(getModel(), rolesId);
		return "tolist";
	}
	
	/**
	 * 用户修改密码
	 * 
	 * @return
	 */
	public String modifyPassword() {
		// 获取当前登录的用户
		User loginUser = (User) BosCommonUtils.getSessionValue("user");
		// 获取参数传递的新明文密码
		String newPassword = user.getPassword();
		String res = "1";

		try {
			userService.modifyPassword(loginUser, newPassword);
		} catch (Exception ex) {
			res = "0";
			ex.printStackTrace();
		}
		try {
			BosCommonUtils.getResponse().setContentType("text/html;charset=utf-8");
			BosCommonUtils.getResponseWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 用户注销登录
	 * 
	 * @return
	 */
	public String loginOut() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "tologin";
	}

	/**
	 * 用户登录功能的实现
	 * 
	 * @return
	 */
	public String login() {
		try {
			if (checkCode == null) {
				this.addActionError("请输入验证码！");
				return LOGIN;
			}

			if (user != null) {
				// 检验验证码
				String checkN = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
				if (!StringUtils.isNotBlank(checkN) || !checkCode.equals(checkN)) {
					this.addActionError("验证码输入错误！");
					return LOGIN;
				}

				// 验证码输入正确校验用户
				/////////////////////////shiro重写部分/////////////////////////////////
				Subject subject = SecurityUtils.getSubject();//获取当前登录用户
				//创建认证令牌
				AuthenticationToken token = new UsernamePasswordToken(getModel().getUsername(), MD5Utils.md5(getModel().getPassword()));
				//调用shiro的认证方法---我们自定义的realm中的认证方法
				try{
					subject.login(token);
					//认证通过没有抛出异常---获取用户登录凭证--当前登录用户信息
					User user = (User) subject.getPrincipal();
					ServletActionContext.getRequest().getSession().setAttribute("user", user);
				}catch(Exception e) {
					e.printStackTrace();
					this.addActionError("用户名或密码输入错误！");
					return LOGIN;
				}
				//////////////////////////////////////////////////////////////////////
				return HOME;
			} else {
				this.addActionError("请输入用户名和密码！");
				return LOGIN;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 使用shiro框架重写登录方法/这个是备份
	 * 
	 * @return
	 */
	public String login_bak() {
		try {
			if (checkCode == null) {
				this.addActionError("请输入验证码！");
				return LOGIN;
			}

			if (user != null) {
				// 检验验证码
				String checkN = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
				if (!StringUtils.isNotBlank(checkN) || !checkCode.equals(checkN)) {
					this.addActionError("验证码输入错误！");
					return LOGIN;
				}

				// 验证码输入正确校验用户
				User existU = userService.checkUser(user);

				if (existU == null) {
					this.addActionError("用户名或密码输入错误！");
					return LOGIN;
				}
				ServletActionContext.getRequest().getSession().setAttribute("user", existU);
				return HOME;
			} else {
				this.addActionError("请输入用户名和密码！");
				return LOGIN;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public String[] getRolesId() {
		return rolesId;
	}

	public void setRolesId(String[] rolesId) {
		this.rolesId = rolesId;
	}

}
