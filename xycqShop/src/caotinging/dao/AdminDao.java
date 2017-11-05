package caotinging.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import caotinging.domain.Category;
import caotinging.domain.Order;
import caotinging.domain.Product;

public interface AdminDao {

	/**
	 * 获取商品管理下所有商品信息
	 * @return
	 * @throws SQLException
	 */
	List<Product> findAllProduct() throws SQLException;

	/**
	 * 从数据库获取全部的商品分类信息
	 * @return
	 * @throws SQLException 
	 */
	List<Category> findAllCategory() throws SQLException;

	/**
	 * 将商品存入数据库
	 * @param product
	 * @return
	 * @throws SQLException 
	 */
	int storeProduct(Product product) throws SQLException;

	/**
	 * 查询所有的订单信息
	 * @return
	 * @throws SQLException 
	 */
	List<Order> findAllOrders() throws SQLException;

	/**
	 * 查询订单下的所有订单项
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException;

	/**
	 * 将商品分类存入数据库
	 * @param category
	 * @return
	 * @throws SQLException 
	 */
	int storeCategory(Category category) throws SQLException;

	/**
	 * 修改数据库中指定商品类别信息
	 * @param cid
	 * @param cname
	 * @return
	 * @throws SQLException
	 */
	int exitCategory(String cid, String cname) throws SQLException;

	/**
	 * 删除数据库中指定商品类别信息
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	int delCategory(String cid) throws SQLException;

	/**
	 * 修改数据库中商品信息
	 * @param product
	 * @return
	 */
	int exitProductInfo(Product product) throws SQLException;

}
