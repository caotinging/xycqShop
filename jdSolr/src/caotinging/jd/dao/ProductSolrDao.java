package caotinging.jd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import caotinging.jd.pojo.ProductModel;
import caotinging.jd.pojo.QueryVo;

@Repository
public class ProductSolrDao {

	@Autowired
	private HttpSolrServer solrServer;
	
	public List<ProductModel> solrQuery(QueryVo vo) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		//添加查询条件
		if(!StringUtils.isEmpty(vo.getQueryString())) {
			query.setQuery(vo.getQueryString());
		}
		if(!StringUtils.isEmpty(vo.getPrice())) {
			String[] strs = vo.getPrice().split("-");
			query.set("fq", "product_price:" + "[" + strs[0] + " TO " + strs[1] + "]");
		}
		if(!StringUtils.isEmpty(vo.getCatalog_name())) {
			query.set("fq", "product_catalog_name:" + vo.getCatalog_name());
		}
		if(!StringUtils.isEmpty(vo.getSort())) {
			if(vo.getSort().equals("1")) {
				query.setSort("product_price", ORDER.desc);
			}else {
				query.setSort("product_price", ORDER.asc);
			}
		}
		
		//设置查询指定域
		query.set("fl", "id,product_name,product_price,product_picture");
		//设置默认查询域
		query.set("df", "product_keywords");
		
		//分页查询
		query.setStart(0);
		query.setRows(10);
		
		//高亮
		query.setHighlight(true);
		query.setHighlightSimplePre("<span style='color:red'>");
		query.setHighlightSimplePost("</span>");
		query.addHighlightField("product_name");
		
		//执行查询
		QueryResponse response = solrServer.query(query);
		//获取结果文档集
		SolrDocumentList documentList = response.getResults();
		
		//总条数
//		long numFound = documentList.getNumFound();
		
		//获取高亮域
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		List<ProductModel> list = new ArrayList<ProductModel>();
		for (SolrDocument solrDocument : documentList) {
			ProductModel model = new ProductModel();
			
			model.setPid((String) solrDocument.getFieldValue("id"));
			model.setPrice((float) solrDocument.getFieldValue("product_price"));
			model.setPicture((String) solrDocument.getFieldValue("product_picture"));
			
			//获取高亮域
			Map<String, List<String>> map = highlighting.get(solrDocument.get("id"));
			List<String> list2 = map.get("product_name");
			if(list2 != null)
			model.setName(list2.get(0));
			
			list.add(model);
		}
		
		return list;
	}
}
