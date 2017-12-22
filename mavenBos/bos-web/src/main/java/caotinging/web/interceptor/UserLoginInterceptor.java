package caotinging.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import caotinging.domain.User;
import caotinging.utils.BosCommonUtils;

/**
 * 用户未登录访问拦截器
 * @author caoting
 *
 */
public class UserLoginInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//拦截方法：从session中获取user，为空则拦截
		User user = (User) BosCommonUtils.getSessionValue("user");
		if(user == null) {
			return "tologin";
		}
		return invocation.invoke();
	}
}
