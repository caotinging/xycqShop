package caotinging.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

}
