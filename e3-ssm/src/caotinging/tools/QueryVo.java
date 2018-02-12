package caotinging.tools;

import java.util.List;

import caotinging.pojo.Items;

/**
 * 封装查询条件
 * @author caoting
 *
 */
public class QueryVo {

	private String custName;
	private String custSource;
	private String custIndustry;
	private String custLevel;
	
	private Integer page;//当前页
	private Integer rows;//每页显示数量
	
	private Integer currentPage;
	private Integer pageSize;
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustIndustry() {
		return custIndustry;
	}
	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
		this.setCurrentPage(page);
	}
	
	/*
	 * 计算起始条数
	 */
	public Integer getStartRows() {
		Integer startRows = 0;
		if(pageSize != null && currentPage != null) {
			startRows = (currentPage - 1)*pageSize;
		}
		return startRows;
	}
	
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
