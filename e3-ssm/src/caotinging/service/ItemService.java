package caotinging.service;

import java.util.List;

import caotinging.pojo.Items;

public interface ItemService {

	/**
	 * 获取所有的购物项
	 * @return
	 */
	public List<Items> findAllItems();

	/**
	 * 根据id查询item信息
	 * @param id
	 * @return
	 */
	public Items findItemById(Integer id);

	/**
	 * 更新Item的信息
	 * @param item
	 */
	public void updateItem(Items item);
}
