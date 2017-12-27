package caotinging.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import caotinging.dao.IStaffDao;
import caotinging.dao.base.impl.BaseDaoImpl;
import caotinging.domain.Staff;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> getNDelStaff() {
		String hql = "from caotinging.domain.Staff where deltag = '0'";
		
		List<?> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() != 0) {
			return (List<Staff>) list;
		}
		return null;
	}
}
