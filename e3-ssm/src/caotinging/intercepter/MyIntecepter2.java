package caotinging.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyIntecepter2 implements HandlerInterceptor {

	@Override
	//页面渲染后执行的方法
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("页面渲染后2");
	}

	@Override
	//访问的方法执行完后执行的操作
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("方法执行后2");
	}

	@Override
	//访问的方法执行前的操作。return false表示不放行，true表示放行
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("方法执行前2");

		return true;
	}

}
