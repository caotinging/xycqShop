package cn.com.webxml;

/**
 * 调用手机归属地查询
 * @author caoting
 *
 */
public class App {

	public static void main(String[] args) {
		//用户登录，付费用户可输入用户名和密码获取别致服务
		MobileCodeWS ss = new MobileCodeWS();
		//获取实现方法的代理接口
		MobileCodeWSSoap soap = ss.getMobileCodeWSSoap();
		//调用获取天气的方法
		String info = soap.getMobileCodeInfo("1851166", null);
		System.out.println(info);
	}
	
}
