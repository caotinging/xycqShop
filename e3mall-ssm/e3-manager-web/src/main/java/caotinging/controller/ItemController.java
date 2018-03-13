package caotinging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import caotinging.common.PageQuery;
import caotinging.pojo.Item;
import caotinging.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 根据id获取item对象
	 * @param itemid
	 * @return
	 */
	@RequestMapping("/item/{itemid}")
	public @ResponseBody Item getItemById(@PathVariable Long itemid) {
		Item item = itemService.getItemById(itemid);
		return item;
	}
	
	@RequestMapping("/item/list")
	public @ResponseBody PageQuery<Item> getItemList(Integer page, Integer rows) {
		return itemService.getItemList(page, rows);
	}
}
