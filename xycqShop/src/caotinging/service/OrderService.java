package caotinging.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import caotinging.dao.OrderDao;
import caotinging.domain.Order;
import caotinging.domain.OrderItem;
import caotinging.domain.Product;
import caotinging.domain.User;
import caotinging.utils.DataSourceUtils;

public class OrderService {

	/**
	 * 存储订单信息到数据库
	 * @param order
	 * @return 执行结果
	 */
	public boolean storeOrder(Order order) {
		OrderDao dao = new OrderDao();
		boolean flag = true;
		List<OrderItem> orderItemList = order.getOrderItemList();
		
		try {
			DataSourceUtils.startTransaction();
			
			dao.storeOrder(order);
			for(OrderItem orderItem: orderItemList) {
				dao.storeOrderItem(orderItem);
			}
			
		} catch (SQLException e) {
			flag = false;
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	/**
	 * 完善订单信息，收货人收货地址以及联系电话
	 * @param order
	 */
	public boolean compleOrderInfo(Order order) {
		OrderDao dao = new OrderDao();
		int res=0;
		try {
			res = dao.compleOrderInfo(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res>0?true:false;
	}

	/**
	 * 设置订单的支付状态为已支付
	 * @param r6_Order
	 */
	public void setState(String oid) {
		OrderDao dao = new OrderDao();
		try {
			dao.setState(oid, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得指定用户的所有订单
	 * @param user
	 * @return 该用户的所有订单信息
	 */
	public List<Order> getOrders(User user) {
		OrderDao dao = new OrderDao();
		List<Order> orders = null;
		try {
			orders = dao.getOrders(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * 获取订单下的所有订单项
	 * @param order
	 * @return
	 */
	public List<OrderItem> getOrderItems(Order order) {
		OrderDao dao = new OrderDao();
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		try {
			List<Map<String, Object>> mapList = dao.getOrderItems(order);
			
			for(Map<String, Object> map: mapList) {
				
				try {
					OrderItem orderitem = new OrderItem();
					BeanUtils.populate(orderitem, map);
					
					Product product = new Product();
					BeanUtils.populate(product, map);
					
					orderitem.setProduct(product);
					orderItems.add(orderitem);
					
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderItems;
	}
}
