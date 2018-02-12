package caotinging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.mapper.CustMapper;
import caotinging.pojo.Customer;
import caotinging.service.CustomerService;
import caotinging.tools.Page;
import caotinging.tools.QueryVo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustMapper custMapper;
	
	@Override
	public Page<Customer> findCustList(QueryVo vo) {
		if(vo.getCustName() != null && vo.getCustName().trim().equals("")) {
			vo.setCustName(null);
		}
		if(vo.getCustSource() != null && vo.getCustSource().trim().equals("")) {
			vo.setCustSource(null);
		}
		if(vo.getCustLevel() != null && vo.getCustLevel().trim().equals("")) {
			vo.setCustLevel(null);
		}
		if(vo.getCustIndustry() != null && vo.getCustIndustry().trim().equals("")) {
			vo.setCustIndustry(null);
		}
		if(vo.getCurrentPage() == null || vo.getCurrentPage() <= 0) {
			vo.setCurrentPage(1);
		}
		if(vo.getPageSize() == null || vo.getPageSize() <= 0) {
			vo.setPageSize(5);
		}
		Integer count = custMapper.findCustListCount(vo);
		Page<Customer> page = new Page<Customer>();
		page.setTotal(count);
		page.setSize(vo.getPageSize());
		page.setPage(vo.getCurrentPage());
		
		List<Customer> list = custMapper.findCustList(vo);
		page.setRows(list);
		
		return page;
	}

}
