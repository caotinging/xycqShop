package caotinging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@RequestMapping(value="/getList.action")
	public String getCustList(Model model) {
		return "customer";
	}
}
