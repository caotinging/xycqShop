package caotinging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import caotinging.pojo.Items;
import caotinging.service.ItemService;

@Controller
public class ItemsController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/items/itemList.action")
	public ModelAndView findItemsList(){
		List<Items> list = itemService.findAllItems();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemList", list);
		mav.setViewName("itemList");
		return mav;
	}
}
