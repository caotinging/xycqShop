package caotinging.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IDecidedzoneDao;
import caotinging.dao.INoticebillDao;
import caotinging.dao.IWorkbillDao;
import caotinging.domain.Decidedzone;
import caotinging.domain.Noticebill;
import caotinging.domain.Staff;
import caotinging.domain.User;
import caotinging.domain.Workbill;
import caotinging.service.INoticebillService;
import caotinging.utils.BosCommonUtils;
import caotinging.utils.crm.ICustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

	@Autowired
	private ICustomerService customerServiceProxy;
	@Autowired
	private INoticebillDao noticebillDao;
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private IWorkbillDao workbillDao;
	
	@Override
	public void saveNoticebill(Noticebill model) {
		//为业务通知单关联受理人
		User user = BosCommonUtils.getLoginUser();
		model.setUser(user);
		
		//尝试为业务通知单自动分配取派员
		String address = model.getPickaddress();
		String subAddress = StringUtils.substring(address, 0, 6);
		
		String decidedzoneId = customerServiceProxy.getDecidedzoneIdByAddress(subAddress);
		if(StringUtils.isNotBlank(decidedzoneId)) {
			//可以通过地址自动分单
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			
			if(staff != null) {
				model.setStaff(staff);
				model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
				
				//为取派员产生一个工单
				Workbill workbill = new Workbill();
				workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
				workbill.setAttachbilltimes(0);//追单次数
				workbill.setNoticebill(model);
				workbill.setStaff(staff);
				workbill.setPickstate(Workbill.PICKSTATE_NONE);//工单取件状态
				workbill.setRemark(model.getRemark());
				workbill.setType(Workbill.TYPE_COMMON);
				workbillDao.save(workbill);
				
				//下发短信通知取派员：调用第三方短信平台服务
			}else {
				//定区没有指定取派员:手动分单
				model.setOrdertype(Noticebill.ORDERTYPE_MAN);
			}
		}else {
			//没有匹配到相关定区：手动分单
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
		
		noticebillDao.save(model);
	}

}
