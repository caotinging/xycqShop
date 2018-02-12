package caotinging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import caotinging.pojo.BaseDict;
import caotinging.pojo.Customer;
import caotinging.service.BaseDictService;
import caotinging.service.CustomerService;
import caotinging.tools.Page;
import caotinging.tools.QueryVo;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//解决硬编码
	@Value("${base.custSource}")
	private String custSourceCode;
	@Value("${base.custIndustry}")
	private String custIndustryCode;
	@Value("${base.custLevel}")
	private String custLevelCode;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BaseDictService baseDictService;
	
	/**
	 * 保存客户更新信息
	 * @param customer
	 */
	@RequestMapping(value="/update.action")
	public void updateCustomer(Customer customer) {
		
	}
	
	/**
	 * 根据客户id获取客户信息回显编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit.action")
	public @ResponseBody Customer editCustomer(Integer id){
		Customer customer = customerService.findCustById(id);
		return customer;
	}
	
	/**
	 * 分页显示客户信息
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/getList.action")
	public String getCustList(Model model, QueryVo vo) {
		List<BaseDict> custSourceList = baseDictService.findListByTypeCode(custSourceCode);
		List<BaseDict> custIndustryList = baseDictService.findListByTypeCode(custIndustryCode);
		List<BaseDict> custLevelList = baseDictService.findListByTypeCode(custLevelCode);
		
		model.addAttribute("fromType", custSourceList);
		model.addAttribute("industryType", custIndustryList);
		model.addAttribute("levelType", custLevelList);
		
		Page<Customer> page = customerService.findCustList(vo);
		model.addAttribute("page", page);
		
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custLevel", vo.getCustLevel());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		
		return "customer";
	}
}
