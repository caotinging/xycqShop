package caotinging.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import caotinging.domain.Cart;
import caotinging.domain.CartItem;
import caotinging.domain.Order;
import caotinging.domain.OrderItem;
import caotinging.domain.User;
import caotinging.service.OrderService;
import caotinging.utils.PaymentUtil;
import caotinging.utils.RandomID;

public class OrderServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		
		if(address!=null && name!=null && telephone!=null) {
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			
			OrderService service = new OrderService();
			boolean isSuccess = service.compleOrderInfo(order);
			
			if(isSuccess) {
				service.setState(order.getOid());
				response.sendRedirect(request.getContextPath()+"/paySuccess.jsp");
			}
			//第三方支付平台开关
			boolean flag = false;
			
			if(flag) {
				String orderid = order.getOid();
				//String money = order.getTotal()+"";//支付金额
				String money = "0.01";//支付金额
				// 银行
				String pd_FrpId = request.getParameter("pd_FrpId");

				// 发给支付公司需要哪些数据
				String p0_Cmd = "Buy";
				String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
				String p2_Order = orderid;
				String p3_Amt = money;
				String p4_Cur = "CNY";
				String p5_Pid = "";
				String p6_Pcat = "";
				String p7_Pdesc = "";
				// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
				// 第三方支付可以访问网址
				String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
				String p9_SAF = "";
				String pa_MP = "";
				String pr_NeedResponse = "1";
				// 加密hmac 需要密钥
				String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
						"keyValue");
				String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url,
						p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

				String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
						"&p0_Cmd="+p0_Cmd+
						"&p1_MerId="+p1_MerId+
						"&p2_Order="+p2_Order+
						"&p3_Amt="+p3_Amt+
						"&p4_Cur="+p4_Cur+
						"&p5_Pid="+p5_Pid+
						"&p6_Pcat="+p6_Pcat+
						"&p7_Pdesc="+p7_Pdesc+
						"&p8_Url="+p8_Url+
						"&p9_SAF="+p9_SAF+
						"&pa_MP="+pa_MP+
						"&pr_NeedResponse="+pr_NeedResponse+
						"&hmac="+hmac;

				//重定向到第三方支付平台
				response.sendRedirect(url);
			}
		}
	}
	
	/**
	 * 完成对订单对象Order的封装以及存库操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart != null) {
			Order order = new Order();
			
			Map<String, CartItem> cartItemMap = cart.getCartItemMap();
			for(Entry<String, CartItem> entry: cartItemMap.entrySet()) {
				OrderItem orderItem = new OrderItem();
				
				CartItem cartItem = entry.getValue();
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setCount(cartItem.getQuantity());
				orderItem.setSubtotal(cartItem.getSubtotal());
				
				String itemid = RandomID.getRandomID();
				orderItem.setItemid(itemid);
				orderItem.setOrder(order);
				
				order.getOrderItemList().add(orderItem);
			}
			
			String oid = RandomID.getRandomID();
			order.setOid(oid);
			order.setOrdertime(new Date());
			order.setState(0);
			
			double total = cart.getAmount();
			order.setTotal(total);
			order.setUser(user);
			
			OrderService service = new OrderService();
			boolean isSuccess = service.storeOrder(order);
			
			if(isSuccess) {
				session.setAttribute("order", order);
				response.sendRedirect(request.getContextPath()+"/order_info.jsp");
			} else {
				response.sendRedirect(request.getContextPath()+"/error.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/cart.jsp");
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}
}
