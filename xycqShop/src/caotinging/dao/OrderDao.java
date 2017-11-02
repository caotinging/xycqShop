package caotinging.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import caotinging.domain.Order;
import caotinging.domain.OrderItem;
import caotinging.utils.DataSourceUtils;

public class OrderDao {

	/**
	 * 订单项入库
	 * @param orderItem
	 * @throws SQLException 
	 */
	public void storeOrderItem(OrderItem orderItem) throws SQLException {
		Connection conn = DataSourceUtils.getCurrentConnection();
		QueryRunner qr = new QueryRunner();
		
		String sql = "insert into orderitem values(?,?,?,?,?);";
		qr.update(conn, sql, orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(),
				orderItem.getProduct().getPid(), orderItem.getOrder().getOid());
	}

	/**
	 * 订单入库
	 * @param order
	 * @throws SQLException 
	 */
	public void storeOrder(Order order) throws SQLException {
		Connection conn = DataSourceUtils.getCurrentConnection();
		QueryRunner qr = new QueryRunner();
		
		String sql = "insert into orders values(?,?,?,?,?,?,?,?);";
		qr.update(conn, sql, order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
				order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	/**
	 * 完善订单信息（收货信息）
	 * @param order
	 * @return
	 * @throws SQLException 
	 */
	public int compleOrderInfo(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set address=?, name=?, telephone=? where oid=?;";
		int res = qr.update(sql, order.getAddress(), order.getName(), order.getTelephone(), order.getOid());
		return res;
	}

	/**
	 * 设置订单支付状态
	 * @param oid
	 * @param i 
	 * @throws SQLException 
	 */
	public void setState(String oid, int i) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state=? where oid=?;";
		qr.update(sql, i, oid);
	}
}
