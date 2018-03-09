package caotinging.solrJ;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 测试solrJ的使用
 * @author caoting
 *
 */
public class SolrJTest {
	
	/**
	 * 测试使用solrj进行复杂查询
	 * 其中包含查询、过滤、分页、排序、高亮显示等处理
	 * @throws SolrServerException 
	 */
	@Test
	public void testQuery() throws SolrServerException {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		SolrQuery solrQuery = new SolrQuery();
		
		//设置默认查询域
		solrQuery.set("df", "product_name");
		
		//设置查询关键字
		solrQuery.setQuery("浪漫樱花");
		
		//设置过滤条件
		solrQuery.setFilterQueries("product_catalog_name:幽默杂货");
		
		//设置结果中显示的域
		solrQuery.setFields("id","product_name","product_price","product_catalog_name","product_picture");
		
		//设置分页条件
		solrQuery.setStart(0);
		solrQuery.setRows(10);
		
		//设置高亮显示
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("product_name");//设置高亮显示域
		solrQuery.setHighlightSimplePre("<em color='red'>");//前缀
		solrQuery.setHighlightSimplePost("</em>");//后缀
		
		//设置排序条件
		solrQuery.setSort("product_price", ORDER.asc);//升序
		
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList documentList = response.getResults();
		System.out.println(documentList.getNumFound());
		for (SolrDocument solrDocument : documentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("product_name"));
			System.out.println(solrDocument.get("product_price"));
			System.out.println(solrDocument.get("product_catalog_name"));
			System.out.println(solrDocument.get("product_picture"));
			System.out.println("-----------------------------------------");
		}
	}
	
	/**
	 * 测试使用solrJ进行简单查询
	 * @throws SolrServerException 
	 */
	@Test
	public void testEasyQuery() throws SolrServerException {
		//1.创建连接
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		//2.创建查询
		SolrQuery query = new SolrQuery("product_name:浪漫樱花");
		//3.执行查询
		QueryResponse response = solrServer.query(query);
		//4.取出查询结果
		SolrDocumentList documentList = response.getResults();
		//5.打印结果
		System.out.println("共查询到商品数量:" + documentList.getNumFound());
		for (SolrDocument solrDocument : documentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("product_name"));
			System.out.println(solrDocument.get("product_price"));
			System.out.println(solrDocument.get("product_catalog_name"));
			System.out.println(solrDocument.get("product_picture"));
			
		}
	}
	
	/**
	 * 修改索引与添加索引步骤一样，只是id为需要修改的索引的id。即是修改
	 */
	
	/**
	 * 测试根据查询条件删除
	 * @throws Exception 
	 * @throws SolrServerException 
	 */
	@Test
	public void testDelByQuery() throws SolrServerException, Exception {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		solrServer.deleteByQuery("product_name:测试");
		solrServer.commit();
	}

	/**
	 * 测试删除索引（根据id删除）
	 * @throws Exception 
	 * @throws SolrServerException 
	 */
	@Test
	public void testDelById() throws SolrServerException, Exception {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		solrServer.deleteById("ceshi");
		solrServer.commit();
	}
	
	/**
	 * 给索引库添加域
	 * @throws Exception 
	 * @throws SolrServerException 
	 */
	@Test
	public void testAddDoc() throws SolrServerException, Exception {
//		和Solr服务器建立连接。HttpSolrServer对象建立连接。
		//参数：solr的访问地址
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
//		创建一个SolrInputDocument对象，然后添加域。
		SolrInputDocument document = new SolrInputDocument();
		
		document.addField("id", "ceshi");
		document.addField("title_ik", "测试文档");
		document.addField("product_name", "测试产品名字");
		document.addField("product_catalog_name", "测试的内容");
		
//		将SolrInputDocument添加到索引库。
		solrServer.add(document);
//		提交。
		solrServer.commit();
	}
}
