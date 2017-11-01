package caotinging.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import caotinging.domain.Cart;
import caotinging.domain.CartItem;
import caotinging.service.CartService;

public class CartServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 清空购物车所有购物项
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
	}
	
	/**
	 * 删除购物车中的购物项
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		String key = request.getParameter("key");
		if(cart != null && key !=null ) {
			Map<String, CartItem> cartItemMap = cart.getCartItemMap();
			if(cartItemMap.containsKey(key)) {
				CartItem cartItem = cartItemMap.get(key);
				double subtotal = cartItem.getSubtotal();
				cart.setAmount(cart.getAmount()-subtotal);
				cartItemMap.remove(key);
			}
		}
	}

	/**
	 * 添加到购物车的功能
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String pid = request.getParameter("pid");
		double shop_price = Double.parseDouble(request.getParameter("shop_price"));
		
		CartItem cartItem = new CartService().getCartItem(quantity, pid, shop_price);
		
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			cart.getCartItemMap().put(pid, cartItem);
			cart.setAmount(cartItem.getSubtotal());
			
			session.setAttribute("cart", cart);
		} else {
			Map<String, CartItem> cartItemMap = cart.getCartItemMap();
			double newSubtotal = cartItem.getSubtotal();
			if(cartItemMap.containsKey(pid)) {
				CartItem oldCartItem = cartItemMap.get(pid);
				
				int oldQuantity = oldCartItem.getQuantity();
				oldCartItem.setQuantity(oldQuantity+quantity);
				
				double oldSubtotal = oldCartItem.getSubtotal();
				oldCartItem.setSubtotal(oldSubtotal+newSubtotal);
			} else {
				cartItemMap.put(pid, cartItem);
			}
			
			cart.setAmount(cart.getAmount()+newSubtotal);
		}
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}
	
}
