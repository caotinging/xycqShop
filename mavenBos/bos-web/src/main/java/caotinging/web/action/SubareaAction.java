package caotinging.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Subarea;
import caotinging.service.ISubareaService;
import caotinging.utils.BosCommonUtils;
import caotinging.utils.FileUtils;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISubareaService subareaService;
	private Subarea subarea = super.getModel();
	
	private String decidedzoneId;
	
	/**
	 * 通过指定的定区获取关联的分区的信息
	 * @return
	 */
	public String getListBydecidedzoneId() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		
		List<Subarea> list = subareaService.getListBydecidedzoneId(criteria);
		java2JsonWrite(list, new String[]{"subareas", "decidedzone"});
		return NONE;
	}

	/**
	 * 生成xls文件提供下载
	 * @return
	 */
	public String exportXls() {
		//将xls文件传输到service层进行数据包装
		HSSFWorkbook workbook = new HSSFWorkbook();
		subareaService.exportAllToXls(workbook);
		
		//使用输出流进行文件下载：（一个流，两个头）
		String filename = "分区数据.xls";
		//获取文件的类型表示
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		
		String agent = BosCommonUtils.getRequest().getHeader("User-Agent");
		//解决中文乱码问题
		try {
			//根据不同的浏览器进行转码
			filename = FileUtils.encodeDownloadFilename(filename, agent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BosCommonUtils.getResponse().setContentType(mimeType);
		BosCommonUtils.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		
		//获取输出流
		try {
			ServletOutputStream out = BosCommonUtils.getResponse().getOutputStream();
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
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

	public String getDecidedzoneId() {
		return decidedzoneId;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

}
