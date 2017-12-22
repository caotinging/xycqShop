package caotinging.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

/**
 * 本BOS项目常用方法工具类
 * @author caoting
 */
public class BosCommonUtils {
	
	/**
	 * 获取原生request对象
	 * @return 
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取原生response对象
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 获取原生session对象
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 获取resopnse的writer
	 * @return
	 * @throws IOException
	 */
	public static PrintWriter getResponseWriter() throws IOException {
		return BosCommonUtils.getResponse().getWriter();
	}
	
	/**
	 * 获取原生session域中的对象
	 * @param keyName
	 * @return
	 */
	public static Object getSessionValue(String keyName) {
		return BosCommonUtils.getSession().getAttribute(keyName);
	}
}
