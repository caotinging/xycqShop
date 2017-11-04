package caotinging.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import caotinging.dao.AdminDao;
import caotinging.domain.Category;
import caotinging.domain.Order;
import caotinging.domain.Product;
import caotinging.service.AdminService;
import caotinging.utils.BeanFactory;

public class AdminServiceImpl implements AdminService {

	private AdminDao dao = (AdminDao) BeanFactory.getBean("adminDao");

	@Override
	public List<Product> findAllProduct() {
		List<Product> productList = null;
		try {
			productList = dao.findAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<Category> findAllCategory() {
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public boolean storeProduct(Product product) {
		int res = 0;
		try {
			res = dao.storeProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res>0?true:false;
	}

	@Override
	public List<Order> findAllOrders() {
		List<Order> orders = null;
		try {
			orders = dao.findAllOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		List<Map<String, Object>> orderItemMaps = null;
		try {
			orderItemMaps = dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemMaps;
	}

}
