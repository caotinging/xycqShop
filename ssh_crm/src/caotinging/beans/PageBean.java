package caotinging.beans;

import java.util.List;

public class PageBean<T> {

	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;
	private Integer pageCount;
	private List<T> beanList;
	
	public PageBean(Integer currentPage, Integer pageCount, Integer totalCount) {
		Integer totalPage = null;
		
		if(totalCount <= 0 ) {
			currentPage = 1;
			totalCount = null;
		}else {
			if(pageCount == null) {
				//默认每页条数为3
				pageCount = 3;
			}
			
			totalPage = (totalCount+pageCount-1)/pageCount;
			if(currentPage == null){
				currentPage = 1;
			}
			if(currentPage < 1){
				currentPage = 1;
			}
			if(currentPage > totalPage) {
				currentPage = totalPage;
			}
		}
		
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.totalPage = totalPage;
	}
	
	/**
	 * 获取当前页的起始索引
	 * @return
	 */
	public Integer getStart() {
		return (this.currentPage-1)*this.pageCount;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
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
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
