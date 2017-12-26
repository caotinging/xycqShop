package caotinging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.ISubareaDao;
import caotinging.domain.Subarea;
import caotinging.service.ISubareaService;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Autowired
	private ISubareaDao subareaDao;
	
	@Override
	public void saveSubarea(Subarea subarea) {
		subareaDao.save(subarea);
	}

}
