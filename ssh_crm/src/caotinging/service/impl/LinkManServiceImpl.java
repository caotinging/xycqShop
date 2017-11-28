package caotinging.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.beans.PageBean;
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

	@Override
	public PageBean<LinkMan> getLkmList(DetachedCriteria criteria, Integer currentPage, Integer pageCount) {
		Long count = linkManDao.getCount(criteria);
		
		if(count != 0) {
			Integer totalCount = count.intValue();
			PageBean<LinkMan> bean = new PageBean<LinkMan>(currentPage, pageCount, totalCount);
			List<LinkMan> lkmList = linkManDao.getList(criteria, bean.getStart(), bean.getPageCount());
			bean.setBeanList(lkmList);
			return bean;
		}
		else
			return null;
	}

	@Override
	public LinkMan getLinkManById(Long lkm_id) {
		LinkMan linkMan = linkManDao.findById(lkm_id);
		return linkMan;
	}

}
