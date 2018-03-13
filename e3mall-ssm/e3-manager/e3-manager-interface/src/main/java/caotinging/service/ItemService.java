package caotinging.service;

import caotinging.common.PageQuery;
import caotinging.pojo.Item;

public interface ItemService {

	Item getItemById(Long itemid);

	PageQuery<Item> getItemList(Integer page, Integer rows);
}
