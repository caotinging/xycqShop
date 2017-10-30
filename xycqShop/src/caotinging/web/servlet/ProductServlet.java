package caotinging.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import caotinging.domain.Category;
import caotinging.domain.PageItem;
import caotinging.service.ProductService;
import caotinging.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

public class ProductServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取相应商品类别下的商品分页数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pageItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		
		Jedis jedis = JedisPoolUtils.getJedis();
		String cid = request.getParameter("cid");
		
		String pageItemsJson = jedis.get(cid);
		if(pageItemsJson == null) {
			List<PageItem> pageItemList = service.getpageItems(cid);
			Gson gson = new Gson();
			pageItemsJson = gson.toJson(pageItemList);
			jedis.set(cid, pageItemsJson);
		}
		
		request.setAttribute("pageItems", pageItemsJson);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}
	
	/**
	 * 获取所有的商品的类别
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void categoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		Jedis jedis = JedisPoolUtils.getJedis();
		
		String categoryListJson = jedis.get("categoryList");
		
		if(categoryListJson == null) {
			List<Category> categoryList = service.getCategoryList();
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);
			
			jedis.set("categoryList", categoryListJson);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}
}
