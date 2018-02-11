package caotinging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import caotinging.pojo.BaseDict;
import caotinging.service.BaseDictService;
import caotinging.service.CustomerService;
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
	
	@RequestMapping(value="/getList.action")
	public String getCustList(Model model, QueryVo vo) {
		List<BaseDict> custSourceList = baseDictService.findListByTypeCode(custSourceCode);
		List<BaseDict> custIndustryList = baseDictService.findListByTypeCode(custIndustryCode);
		List<BaseDict> custLevelList = baseDictService.findListByTypeCode(custLevelCode);
		
		model.addAttribute("fromType", custSourceList);
		model.addAttribute("industryType", custIndustryList);
		model.addAttribute("levelType", custLevelList);
		
		return "customer";
	}
}
