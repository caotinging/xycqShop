package caotinging.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import caotinging.dao.AdminDao;
import caotinging.domain.Category;
import caotinging.domain.Order;
import caotinging.domain.Product;
import caotinging.utils.DataSourceUtils;

public class AdminDaoImpl implements AdminDao {

	private QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	
	@Override
	public List<Product> findAllProduct() throws SQLException {
		String sql = "select * from product;";
		List<Product> productList = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return productList;
	}

	@Override
	public List<Category> findAllCategory() throws SQLException {
		String sql = "select * from category;";
		List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return categoryList;
	}

	@Override
	public int storeProduct(Product product) throws SQLException {
		String sql = "insert into product values (?,?,?,?,?,?,?,?,?,?);";
		int res = qr.update(sql, product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(), 
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCategory().getCid());
		return res;
	}

	@Override
	public List<Order> findAllOrders() throws SQLException {
		String sql = "select * from orders;";
		List<Order> orders = qr.query(sql, new BeanListHandler<Order>(Order.class));
		return orders;
	}

	@Override
	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException {
		String sql = "select p.pimage,p.pname,p.shop_price,item.count,item.subtotal from product p , orderitem item WHERE item.oid =? and p.pid=item.pid;";
		List<Map<String, Object>> query = qr.query(sql, new MapListHandler(), oid);
		return query;
	}

}
