package caotinging.service.impl;

import javax.annotation.Resource;

import caotinging.dao.LinkManDao;
import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;

public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;
	
	@Resource(name="linkManDao")
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public Boolean saveOrUpdate(LinkMan linkMan) {
		return linkManDao.saveOrUpdate(linkMan);
	}

}
