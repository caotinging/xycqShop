package caotinging.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping("/login.action")
	public String userLogin(String username, String password, HttpSession session) {
		//假装校验用户名和密码
		System.out.println(username+":"+password);
		
		session.setAttribute("user", username);
		return "redirect:/items/itemList.action";
	}
}
