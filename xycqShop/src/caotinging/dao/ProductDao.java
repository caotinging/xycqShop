package caotinging.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import caotinging.domain.Category;
import caotinging.domain.Product;
import caotinging.utils.DataSourceUtils;

public class ProductDao {

	private final QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	
	/**
	 * 从数据库中获取当前分类九个热门商品
	 * @return 热门商品信息
	 * @throws SQLException
	 */
	public List<Product> getHotProList() throws SQLException {
		String sql = "select * from product where is_hot=1 limit ?,?";
		List<Product> hot_pro_list = qr.query(sql, new BeanListHandler<Product>(Product.class), 0, 9);
		return hot_pro_list;
	}

	/**
	 * 从数据库中获取最新的九个商品
	 * @return 最新商品信息
	 * @throws SQLException
	 */
	public List<Product> getNewProList() throws SQLException {
		String sql = "select * from product order by pdate desc limit ?,?";
		List<Product> new_pro_list = qr.query(sql, new BeanListHandler<Product>(Product.class), 0, 9);
		return new_pro_list;
	}

	/**
	 * 从数据库中查询所有商品类别
	 * @return
	 * @throws SQLException
	 */
	public List<Category> getCategoryList() throws SQLException {
		String sql = "select * from category";
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}

	/**
	 * 查询指定商品分类下指定分页商品的信息
	 * @param cid 
	 * @param countProPage 
	 * @param currentPage 
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> getProductListByCid(String cid, int currentPage, int countProPage) throws SQLException {
		String sql = "select * from product where cid=? limit ?,?";
		
		int index = (currentPage-1)*countProPage;
		List<Product> productList = qr.query(sql, new BeanListHandler<Product>(Product.class), cid, index, countProPage);
		return productList;
	}

	/**
	 * 获取指定商品类别下的所有商品个数
	 * @param cid
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalProductOfCid(String cid) throws SQLException {
		String sql = "select count(*) from product where cid=?";
		Long totalProduct = (Long) qr.query(sql, new ScalarHandler(), cid);
		return totalProduct.intValue();
	}

	/**
	 * 通过商品id获取指定商品信息
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Product getProductById(String pid) throws SQLException {
		String sql = "select * from product where pid=?";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
		return product;
	}
}
