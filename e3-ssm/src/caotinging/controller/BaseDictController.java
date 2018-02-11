package caotinging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import caotinging.service.BaseDictService;

@Controller
@RequestMapping("/baseDict")
public class BaseDictController {

	@Autowired
	private BaseDictService baseDictService;
	
	@RequestMapping(value="/getList.action")
	public String getBaseDict(Model model) {
		return null;
	}
}
