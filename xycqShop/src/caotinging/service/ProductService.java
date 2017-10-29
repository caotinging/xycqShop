package caotinging.service;

import java.sql.SQLException;
import java.util.List;

import caotinging.dao.ProductDao;
import caotinging.domain.Product;

public class ProductService {

	private final ProductDao dao = new ProductDao();
	
	/**
	 * 获取当前商品分类中九个热门商品
	 * @return 热门商品信息
	 */
	public List<Product> getHotProList() {
		List<Product> hot_pro_list = null;
		try {
			hot_pro_list = dao.getHotProList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hot_pro_list;
	}

	/**
	 * 获取最新的商品
	 * @return 最新商品信息
	 */
	public List<Product> getNewProList() {
		List<Product> new_pro_list = null;
		try {
			new_pro_list = dao.getNewProList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new_pro_list;
	}

}
