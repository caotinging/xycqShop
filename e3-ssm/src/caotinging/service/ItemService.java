package caotinging.service;

import java.util.List;

import caotinging.pojo.Items;

public interface ItemService {

	/**
	 * 获取所有的购物项
	 * @return
	 */
	public List<Items> findAllItems();
}
