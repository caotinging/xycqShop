package caotinging.domain;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PageItem implements Serializable {
	private int totalPage;
	private int totalProduct;
	private int currentPage;
	private int countProPage;
	private List<Product> productList;
	private String cid;
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
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
}
