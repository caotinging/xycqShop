package caotinging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IStaffDao;
import caotinging.domain.Staff;
import caotinging.service.IStaffService;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void saveStaff(Staff staff) {
		staffDao.save(staff);
	}

}
