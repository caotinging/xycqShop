package caotinging.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import caotinging.pojo.Items;
import caotinging.service.ItemService;
import caotinging.tools.QueryVo;

@Controller
//@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/items/deleteitem.action")
	public String deleteItem(Model model, Integer[] ids) {
		for (Integer id : ids) {
			itemService.deleteItem(id);
		}
		return "redirect:/items/itemList.action";
	}
	
	/**
	 * 使用包装pojo类接收参数
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/items/updateItem2.action")
	public String updateItem2(QueryVo vo) {
		itemService.updateItem(vo.getItem());
		return "success";
	}
	
	/**
	 * 更新item数据信息
	 * @param item
	 * @return
	 */
	@RequestMapping(value="/items/updateItem.action")
	public ModelAndView updateItem(Items item){
		ModelAndView mav = new ModelAndView();
		try{
			itemService.updateItem(item);
			mav.setViewName("success");
		}catch(Exception ex){
			ex.printStackTrace();
			mav.setViewName("fail");
		}
		return mav;
	}
	
	/**
	 * 对商品信息进行修改的第二种方法：model作为存储数据的对象（也可用实现类ModelMap），返回的String类型作为View
	 * 形参id与提交的参数名相同，自动映射。形参中保存request提交的参数id
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/items/itemEdit2.action")
	public String toEditItem2(Integer id, Model model) {
		Items items = itemService.findItemById(id);
		model.addAttribute("item", items);
		return "editItem2";
	}
	
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
