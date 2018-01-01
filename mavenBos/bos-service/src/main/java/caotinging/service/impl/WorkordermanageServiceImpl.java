package caotinging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IWorkordermanageDao;
import caotinging.domain.Workordermanage;
import caotinging.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

	@Autowired
	private IWorkordermanageDao workordermanageDao;
	
	@Override
	public void quickSave(Workordermanage model) {
		workordermanageDao.save(model);
	}

}
