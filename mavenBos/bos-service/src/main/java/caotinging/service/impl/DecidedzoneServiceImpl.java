package caotinging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IDecidedzoneDao;
import caotinging.dao.IStaffDao;
import caotinging.dao.ISubareaDao;
import caotinging.domain.Decidedzone;
import caotinging.domain.Staff;
import caotinging.domain.Subarea;
import caotinging.service.IDecidedzoneService;
import caotinging.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private IStaffDao staffDao;
	@Autowired
	private ISubareaDao subareaDao;
	
	@Override
	public List<Staff> getNDelStaff() {
		List<Staff> list = staffDao.getNDelStaff();
		return list;
	}

	@Override
	public List<Subarea> getNDidSubarea() {
		return subareaDao.getNDidSubarea();
	}

	@Override
	public void saveDecidedzone(Decidedzone model, String staffid, String[] subareaid) {
		if( staffid != null ) {
			Staff staff = staffDao.findById(staffid);
			model.setStaff(staff);
		}
		
		if(subareaid != null && subareaid.length != 0) {
			for(String subId : subareaid ) {
				Subarea subarea = subareaDao.findById(subId);
				//这里decidedzone作为一的一方是放弃了对外键的维护权的，所以这里应该有subarea来维护自己的外键
				subarea.setDecidedzone(model);
			}
		}
		
		//最后的保存
		decidedzoneDao.save(model);
	}

	@Override
	public void getPageList(PageBean<Decidedzone> pageBean) {
		decidedzoneDao.queryPageBeanList(pageBean);
	}
}
