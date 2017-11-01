package caotinging.web.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import caotinging.domain.Category;
import caotinging.domain.PageItem;
import caotinging.domain.Product;
import caotinging.service.ProductService;
import caotinging.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

public class ProductServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public void historyProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String pid = request.getParameter("pid");
		ProductService service = new ProductService();
		
		if(pid != null) {
			Product product = service.getProductById(pid);
			LinkedList<Product> historyProductList = (LinkedList<Product>) session.getAttribute("historyProductList");
			
			if(historyProductList != null) {
				for(int i=0; i<historyProductList.size(); i++) {
					String hisPid = historyProductList.get(i).getPid();
					if(hisPid.equals(pid))
						historyProductList.remove(i);
				}
				historyProductList.addFirst(product);
			} else {
				historyProductList = new LinkedList<Product>();
				historyProductList.add(product);
			}
			
			session.setAttribute("historyProductList", historyProductList);
		}
	}
	
	/**
	 * 获取指定的商品的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void productById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService service = new ProductService();
		String pid = request.getParameter("pid");
		String currentPage = request.getParameter("currentPage");
		if(pid!=null) {
			Product product = service.getProductById(pid);
			request.setAttribute("product", product);
			String url = "/product_info.jsp?currentPage="+currentPage;
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
	}
	
	/**
	 * 获取相应商品类别下的商品分页数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pageItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		
		String cid = request.getParameter("cid");
		int currentPage = 1;
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		int countProPage = 12;
		
		PageItem pageItem = service.getpageItems(cid, currentPage, countProPage);
		
		request.setAttribute("pageItem", pageItem);
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
