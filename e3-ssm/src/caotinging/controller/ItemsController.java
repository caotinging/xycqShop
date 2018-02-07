package caotinging.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import caotinging.pojo.Items;
import caotinging.service.ItemService;
import caotinging.tools.QueryVo;

@Controller
//@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 测试json数据交互
	 * RequestBody标签将json字符串转换为Item对象item
	 * ResposeBody标签将Item对象转换为json字符串
	 * 如果不是以为配置了<mvc:annotation-driven />还需要手动配置json转换器
	 * @param item
	 * @return
	 */
	@RequestMapping(value="/jsonTest.action")
	public @ResponseBody Items jsonTest(@RequestBody Items item) {
		System.out.println(item);
		return item;
	}
	
	/**
	 * 批量修改操作，只进行演示不进行数据库操作
	 * 接收List类型的数据必须是pojo的属性，方法的形参为ArrayList类型无法正确接收到数据
	 * RequestMapping(method:指定请求的方式只能为--这里是get、post)
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/items/editItemList.action",method={RequestMethod.GET,RequestMethod.POST})
	public String editItemList(QueryVo vo) {
		System.out.println(vo.getItemList().get(0));
		return "redirect:/items/itemList.action";
	}
	
	/**
	 * 批量删除操作
	 * @param model
	 * @param ids 接收要删除的所有item的id值
	 * @return
	 */
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
	public ModelAndView updateItem(Items item,MultipartFile multipartFile){
		ModelAndView mav = new ModelAndView();
		try{
			//上传商品图片
			//重命名图片名称
			String filename = UUID.randomUUID().toString().replace("-", "")+"."+
					FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			//保存图片
			multipartFile.transferTo(new File("D:\\upload\\e3-ssm\\"+filename));
			//将图片访问路径保存到数据库
			item.setPic("pic/"+filename);
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
//		Integer i = 1/0;	异常测试
		List<Items> list = itemService.findAllItems();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemList", list);
		mav.setViewName("itemList");
		return mav;
	}
}
