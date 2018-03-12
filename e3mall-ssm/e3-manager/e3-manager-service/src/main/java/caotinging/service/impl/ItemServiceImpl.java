package caotinging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caotinging.mapper.ItemMapper;
import caotinging.pojo.Item;
import caotinging.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public Item getItemById(Long itemid) {
		Item item = itemMapper.selectByPrimaryKey(itemid);
		return item;
	}

	
}
