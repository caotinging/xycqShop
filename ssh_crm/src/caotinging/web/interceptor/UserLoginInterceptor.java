package caotinging.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 用户是否登录拦截器
 * @author caoting
 *
 */
public class UserLoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Object obj = ActionContext.getContext().getSession().get("user");
		if(obj == null) {
			return "toLogin";
		}
		return invocation.invoke();
	}

}
