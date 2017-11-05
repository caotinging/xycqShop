package caotinging.service;

import java.util.List;
import java.util.Map;

import caotinging.domain.Category;
import caotinging.domain.Order;
import caotinging.domain.Product;

public interface AdminService {

	/**
	 * 获取商品管理下所有商品信息
	 * @return
	 */
	List<Product> findAllProduct();

	/**
	 * 获取所有商品的分类信息
	 * @return
	 */
	List<Category> findAllCategory();

	/**
	 * 存储一个商品到数据库
	 * @param product
	 * @return
	 */
	boolean storeProduct(Product product);

	/**
	 * 查询所有订单信息
	 * @return
	 */
	List<Order> findAllOrders();

	/**
	 * 查询指定订单下的订单项信息
	 * @param oid
	 * @return
	 */
	List<Map<String, Object>> findOrderInfoByOid(String oid);

	/**
	 * 新的商品分类入库
	 * @param cname
	 * @return
	 */
	boolean storeCategory(String cname);

	/**
	 * 修改指定商品分类的信息
	 * @param cid
	 * @param cname
	 * @return
	 */
	boolean exitCategory(String cid, String cname);

	/**
	 * 删除指定商品类别信息
	 * @param cid
	 * @return
	 */
	boolean delCategory(String cid);

	/**
	 * 修改商品信息
	 * @param product
	 * @return
	 */
	boolean exitProductInfo(Product product);

}
