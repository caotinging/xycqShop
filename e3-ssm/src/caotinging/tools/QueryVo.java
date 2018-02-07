package caotinging.tools;

import java.util.List;

import caotinging.pojo.Items;

public class QueryVo {

	private Items item;
	
	private List<Items> itemList;

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}
}
