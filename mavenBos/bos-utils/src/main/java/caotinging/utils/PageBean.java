package caotinging.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean<T> {
	private Integer currentPage;
	private Integer pageCount;
	private List<T> rows;
	private Integer total;
	private DetachedCriteria criteria;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public DetachedCriteria getCriteria() {
		return criteria;
	}
	public void setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
	}

}
