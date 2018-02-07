package caotinging.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 两个拦截器的方法的执行顺序如下：
 *  方法执行前1
	方法执行前2
	方法执行后2
	方法执行后1
	页面渲染后2
	页面渲染后1
	
	递归调用的感觉
	所有拦截器都通过才能执行每个拦截器的方法执行后方法
	页面渲染后方法只需要所属拦截器放行就会执行不需要所有拦截器放行
 * @author caoting
 *
 */
public class MyIntecepter1 implements HandlerInterceptor {

	@Override
	//页面渲染后执行的方法
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("页面渲染后1");
	}

	@Override
	//访问的方法执行完后执行的操作
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("方法执行后1");
	}

	@Override
	//访问的方法执行前的操作。return false表示不放行，true表示放行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println("方法执行前1");
		String requestURI = request.getRequestURI();
		if(requestURI.contains("/login.action")) {
			//如果是登录方法
			return true;
		}else {
			String user = (String) request.getSession().getAttribute("user");
			if(user != null && user != "") {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return false;
	}
}
