package caotinging.service;

import java.sql.SQLException;
import java.util.List;

import caotinging.dao.ProductDao;
import caotinging.domain.Category;
import caotinging.domain.PageItem;
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

	/**
	 * 获取所有的商品类别
	 * @return
	 */
	public List<Category> getCategoryList() {
		List<Category> categoryList = null;
		try {
			categoryList = dao.getCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	/**
	 * 获取该商品分类下的所有商品分页信息
	 * @param cid
	 * @param countProPage 
	 * @param currentPage 
	 * @return
	 */
	public PageItem getpageItems(String cid, int currentPage, int countProPage) {
		PageItem pageItem = new PageItem();
		try {
			List<Product> productList = dao.getProductListByCid(cid, currentPage, countProPage);
			
			pageItem.setCurrentPage(currentPage);
			pageItem.setCountProPage(countProPage);
			pageItem.setProductList(productList);
			
			int totalProduct = dao.getTotalProductOfCid(cid);
			pageItem.setTotalProduct(totalProduct);
			
			int totalPage = (int) Math.ceil(1.0*totalProduct/countProPage);
			pageItem.setTotalPage(totalPage);
			
			pageItem.setCid(cid);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pageItem;
	}

	/**
	 * 通过id获取指定商品信息
	 * @param pid
	 * @return 该id对应的商品信息
	 */
	public Product getProductById(String pid) {
		Product product = null;
		try {
			product = dao.getProductById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

}
