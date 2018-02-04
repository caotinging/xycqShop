package caotinging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.mapper.ItemsMapper;
import caotinging.pojo.Items;
import caotinging.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<Items> findAllItems() {
		List<Items> list = itemsMapper.selectByExampleWithBLOBs(null);
		return list;
	}

}
