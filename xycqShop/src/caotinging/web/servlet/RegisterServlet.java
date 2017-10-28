package caotinging.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import caotinging.domain.User;
import caotinging.service.UserService;
import caotinging.utils.MailUtils;
import caotinging.utils.RandomID;

/**
 * 用户注册信息的录入
 * @author caoting
 *
 */
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> properties = request.getParameterMap();
		User user = new User();
		try {
			//自定义注册Date类型转换器
			ConvertUtils.register(new Converter() {
				
				@SuppressWarnings("rawtypes")
				@Override
				public Object convert(Class arg0, Object value) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					try {
						date = format.parse(value.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return date;
				}
			}, Date.class);
			
			BeanUtils.populate(user , properties);
			if(user != null) {
				user.setUid(RandomID.getRandomID());
				user.setState(0);
				user.setTelephone(null);
				user.setCode(RandomID.getRandomID());
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		UserService service = new UserService();
		boolean isSuccess = service.register(user);
		
		if(isSuccess) {
			String userCode = user.getCode();
			
			String emailMsg = "恭喜您，注册成功！点击链接激活账户<a href='http://localhost:8080/xycqShop/servlet/login?code="+userCode+"'>" + "http://localhost:8080/xycqShop/servlet/login?code=" +userCode+ "</a>";
			//发送激活邮件
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg );
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp?email="+user.getEmail());
		} else {
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
