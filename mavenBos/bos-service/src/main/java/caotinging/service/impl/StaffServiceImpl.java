package caotinging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IStaffDao;
import caotinging.domain.Staff;
import caotinging.service.IStaffService;
import caotinging.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void saveStaff(Staff staff) {
		staffDao.save(staff);
	}

	@Override
	public void getList(PageBean<Staff> pageBean) {
		staffDao.queryPageBeanList(pageBean);
	}

	@Override
	public void deleteByIds(String ids) {
		String[] strArr = ids.split(",");
		for(String id : strArr) {
			staffDao.executeUpdate("staff.deleteStaff", id);
		}
	}

	@Override
	public void updateStaff(Staff staff) {
		String id = staff.getId();
		Staff oldStaff = staffDao.findById(id);
		
		oldStaff.setName(staff.getName());
		oldStaff.setStandard(staff.getStandard());
		oldStaff.setTelephone(staff.getTelephone());
		oldStaff.setHaspda(staff.getHaspda());
		oldStaff.setStation(staff.getStation());
		
		//更新staff
		staffDao.update(oldStaff);
	}

}
