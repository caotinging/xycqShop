package caotinging.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 本BOS项目常用方法工具类
 * @author caoting
 */
public class BosCommonUtils {
	
	/**
	 * 将分页数据转换为json数据
	 * @param pageBean 分页数据
	 * @param strArr 需要排除的属性
	 * @return
	 */
	public static <T> String pageBeanJson(PageBean<T> pageBean, String[] strArr) {
		String json = "";
		
		//把查询的数据封装成json格式
		JsonConfig config = new JsonConfig();
		config.setExcludes(strArr);
		
		json = JSONObject.fromObject(pageBean ,config).toString();
		return json;
	}
	
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
