package caotinging.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import caotinging.domain.Category;
import caotinging.domain.Order;
import caotinging.domain.Product;
import caotinging.service.AdminService;
import caotinging.utils.BeanFactory;

public class AdminServlet extends BaseServlet {
	
	/**
	 * 删除商品分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		String cid = request.getParameter("cid");
		if(cid != null) {
			boolean isSuccess = service.delCategory(cid);
			if(isSuccess) {
				response.sendRedirect(request.getContextPath()+"/admin/category/list.jsp");
			}else {
				response.sendRedirect(request.getContextPath()+"/error.jsp");
			}
		}
	}
	
	/**
	 * 修改商品分类的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void exitCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		
		if(cid != null && cname != null) {
			boolean isSuccess = service.exitCategory(cid, cname);
			if(isSuccess) {
				response.sendRedirect(request.getContextPath()+"/admin/category/list.jsp");
			}else {
				response.sendRedirect(request.getContextPath()+"/error.jsp");
			}
		}
	}
	
	/**
	 * 存储一个新的商品分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void storeCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		
		String cname = request.getParameter("cname");
		if(cname != null) {
			boolean isSuccess = service.storeCategory(cname);
			if(isSuccess) {
				response.sendRedirect(request.getContextPath()+"/admin/category/list.jsp");
			}else {
				response.sendRedirect(request.getContextPath()+"/error.jsp");
			}
		}
	}
	
	/**
	 * 根据订单id获取该订单中订单项信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findOrderInfoByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		
		String oid = request.getParameter("oid");
		List<Map<String, Object>> orderItemMaps = service.findOrderInfoByOid(oid);
		
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		String json = gson.toJson(orderItemMaps);
		
		response.getWriter().write(json);
	}
	
	/**
	 * 获取所有订单信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		
		List<Order> orders = service.findAllOrders();
		
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);
	}
	
	/**
	 * 获取所有商品类别
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		
		List<Category> categoryList = service.findAllCategory();
		
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		String json = gson.toJson(categoryList);
		
		response.getWriter().write(json);
	}
	
	/**
	 * 获得商品管理页面所有商品展示信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		
		List<Product> productList = service.findAllProduct();
		Gson gson = new Gson();
		String json = gson.toJson(productList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
