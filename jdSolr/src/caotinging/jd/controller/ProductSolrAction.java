package caotinging.jd.controller;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import caotinging.jd.pojo.ProductModel;
import caotinging.jd.pojo.QueryVo;
import caotinging.jd.service.ProductSolrService;

@Controller
public class ProductSolrAction {

	@Autowired
	private ProductSolrService solrService;
	
	@RequestMapping(value="list.action")
	public String solrQuery(QueryVo vo, Model model) throws SolrServerException {
		List<ProductModel> list = solrService.solrQuery(vo);
		
		model.addAttribute("productModels", list);
		model.addAttribute("queryString", vo.getQueryString());
		model.addAttribute("catalog_name", vo.getCatalog_name());
		model.addAttribute("price", vo.getPrice());
		model.addAttribute("sort", vo.getSort());
		return "product_list";
	}
}
