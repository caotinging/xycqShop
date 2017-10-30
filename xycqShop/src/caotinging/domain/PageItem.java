package caotinging.domain;

import java.util.List;

public class PageItem {
	private int totalPage;
	private int totalProduct;
	private int currentPage;
	private int countProPage;
	private List<Product> productList;
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(int totalProduct) {
		this.totalProduct = totalProduct;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCountProPage() {
		return countProPage;
	}
	public void setCountProPage(int countProPage) {
		this.countProPage = countProPage;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
