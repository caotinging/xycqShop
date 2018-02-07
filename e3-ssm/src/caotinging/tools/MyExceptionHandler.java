package caotinging.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常处理器//仅供测试，无实际异常处理逻辑
 * @author caoting
 *
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

	@Override
	/**
	 * request: 原生request		response: 原生response
	 * obj: 产生异常的位置（String类型）：包名+类名+方法名
	 * ex: 产生的异常信息
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception ex) {
		ModelAndView mav = new ModelAndView();
		
		if(ex instanceof Exception) {
			System.out.println(obj);
			mav.addObject("errorMeg", "未知异常");
			mav.setViewName("error");
		}
		return mav;
	}
}
