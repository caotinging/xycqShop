package caotinging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IRegionDao;
import caotinging.domain.Region;
import caotinging.service.IRegionService;
import caotinging.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;
	
	@Override
	public void fileUpload(List<Region> regionList) {
		for(Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void getList(PageBean<Region> pageBean) {
		regionDao.queryPageBeanList(pageBean);
	}

}
