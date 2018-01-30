package caotinging.mapper;

import java.util.List;

import caotinging.pojo.Order;

public interface OrderMapper {

	/**
	 * 获取所有订单信息
	 * @return
	 */
	public List<Order> findAllOrder();
	
	/**
	 * 获取所有订单信息以及订单的下单用户信息
	 * @return
	 */
	public List<Order> findAllOrderUser();
}
