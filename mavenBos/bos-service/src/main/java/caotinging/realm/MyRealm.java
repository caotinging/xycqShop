package caotinging.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

import caotinging.dao.IUserDao;
import caotinging.domain.User;

public class MyRealm extends AuthenticatingRealm {

	@Autowired
	private IUserDao userDao;
	
	@Override
	/**
	 * 用户登录逻辑
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken)
			throws AuthenticationException {
		//首先将认证令牌转为用户名密码令牌
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationtoken;
		//获取用户名
		String username = token.getUsername();
		//通过用户名获取数据库中的用户信息
		User user = userDao.getUserByUserName(username);
		
		if(user == null) {
			return null;
		}
		
		//用户名存在，将用户信息交给shiro框架进行密码比对
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

}
