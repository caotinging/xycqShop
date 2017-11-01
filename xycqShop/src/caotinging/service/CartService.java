package caotinging.service;

import java.sql.SQLException;

import caotinging.dao.ProductDao;
import caotinging.domain.CartItem;
import caotinging.domain.Product;

public class CartService {

	/**
	 * 根据相关信息获取并封装购物项
	 * @param quantity 购物商品数量
	 * @param pid 商品id
	 * @param shop_price 商品价格
	 * @return 返回封装好的购物项对象
	 */
	public CartItem getCartItem(int quantity, String pid, double shop_price) {
		ProductDao dao = new ProductDao();
		CartItem cartItem = null;
		try {
			Product product = dao.getProductById(pid);
			double subtotal = quantity*shop_price;
			
			cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setSubtotal(subtotal);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cartItem;
	}

}
