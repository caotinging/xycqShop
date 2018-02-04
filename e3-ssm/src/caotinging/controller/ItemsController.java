package caotinging.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	
	
	/**
	 * 对商品信息进行修改
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/items/itemEdit.action")
	public ModelAndView toEditItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
//		mav.addObject("itemList", list);
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Items item = itemService.findItemById(id);
		mav.setViewName("editItem");
		mav.addObject("item", item);
		return mav;
	}
	
	/**
	 * 获取所有商品的购买项信息
	 * @return
	 */
	@RequestMapping(value="/items/itemList.action")
	public ModelAndView findItemsList(){
		List<Items> list = itemService.findAllItems();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemList", list);
		mav.setViewName("itemList");
		return mav;
	}
}
