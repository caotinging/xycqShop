package caotinging.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import caotinging.domain.Product;
import caotinging.service.ProductService;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		HttpSession session = request.getSession();
		
		List<Product> hot_pro_list = service.getHotProList();
		List<Product> new_pro_list = service.getNewProList();
		
		session.setAttribute("hotprolist", hot_pro_list);
		session.setAttribute("newprolist", new_pro_list);
		
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
