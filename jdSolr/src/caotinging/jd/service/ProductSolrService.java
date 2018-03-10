package caotinging.jd.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caotinging.jd.dao.ProductSolrDao;
import caotinging.jd.pojo.ProductModel;
import caotinging.jd.pojo.QueryVo;

@Service
public class ProductSolrService {

	@Autowired
	private ProductSolrDao solrDao;
	
	public List<ProductModel> solrQuery(QueryVo vo) throws SolrServerException {
		return solrDao.solrQuery(vo);
	}
}
