package caotinging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import caotinging.common.PageQuery;
import caotinging.mapper.ItemMapper;
import caotinging.pojo.Item;
import caotinging.pojo.ItemExample;
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
	
	/**
	 * page:当前页
	 * rows：每页行数
	 */
	@Override
	public PageQuery<Item> getItemList(Integer page, Integer rows) {
		
		ItemExample example = new ItemExample();
		
		//设置分页。下一次的select将会有分页效果。并且实际返回的是一个page<>对象，只对下一次的select生效
		PageHelper.startPage(page, rows);
		
		List<Item> list = itemMapper.selectByExample(example);
		
		//将实际是page对象的表里不一的list进行包装
		PageInfo<Item> pageInfo = new PageInfo<Item>(list);
		
		//取出查询总数
		long total = pageInfo.getTotal();
		
		PageQuery<Item> pageQuery = new PageQuery<Item>();
		pageQuery.setTotal(total);
		pageQuery.setRows(list);
		
		return pageQuery;
	}
	
}
