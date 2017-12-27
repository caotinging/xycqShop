package caotinging.web.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Subarea;
import caotinging.service.ISubareaService;
import caotinging.utils.BosCommonUtils;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISubareaService subareaService;
	private Subarea subarea = super.getModel();

	/**
	 * 获取subarea对象集合返回页面进行展示
	 * @return
	 */
	public String getPageList() {
		
		if( subarea.getRegion() != null ) {
			// 判断查询条是否存在
			DetachedCriteria criteria = pageBean.getCriteria();
			String province = subarea.getRegion().getProvince();
			String city = subarea.getRegion().getCity();
			String district = subarea.getRegion().getDistrict();
			
			//多表查询----设置其他表的别名
			criteria.createAlias("region", "r");
			if (StringUtils.isNotBlank(province)) {
				criteria.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if (StringUtils.isNotBlank(city)) {
				criteria.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if (StringUtils.isNotBlank(district)) {
				criteria.add(Restrictions.like("r.district", "%" + district + "%"));
			}

			String addresskey = subarea.getAddresskey();
			if (StringUtils.isNotBlank(addresskey)) {
				criteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
			}
			
			pageBean.setCriteria(criteria);
		}
		
		subareaService.getPageList(pageBean);
		String json = BosCommonUtils.pageBeanJson(pageBean,
				new String[] { "currentPage", "pageCount", "criteria", "decidedzone", "subareas" });
		BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
		try {
			BosCommonUtils.getResponseWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 保存分区subarea对象
	 * 
	 * @return
	 */
	public String save() {
		if (subarea != null) {
			subareaService.saveSubarea(subarea);
		}
		return "tolist";
	}

}
