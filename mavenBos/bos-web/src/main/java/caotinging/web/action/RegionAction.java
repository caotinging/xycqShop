package caotinging.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Region;
import caotinging.service.IRegionService;
import caotinging.utils.BosCommonUtils;
import caotinging.utils.PinYin4jUtils;
import caotinging.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	private static final long serialVersionUID = 1L;

	private File regionFile;
	@Autowired
	private IRegionService regionService;
	// subarea页面的参数：下拉框文本改变的内容
	private String q;

	/**
	 * 根据文本的内容模糊查询获取region对象
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String getListToSubarea() throws IOException {
		if (q != null) {
			// 文本有输入内容，进行模糊查询
			Disjunction disjunction = Restrictions.disjunction();

			disjunction.add(Restrictions.like("province", "%" + q + "%"));
			disjunction.add(Restrictions.like("city", "%" + q + "%"));
			disjunction.add(Restrictions.like("district", "%" + q + "%"));
			disjunction.add(Restrictions.like("shortcode", "%" + q + "%"));
			disjunction.add(Restrictions.like("citycode", "%" + q + "%"));

			criteria.add(disjunction);
		}

		List<Region> list = regionService.getListByCriteria(criteria);
		if (list != null) {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "subareas" });
			String json = JSONArray.fromObject(list, jsonConfig).toString();
			BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
			BosCommonUtils.getResponseWriter().print(json);
		}

		return NONE;
	}

	/**
	 * 区域列表查询
	 * 
	 * @return
	 */
	public String getList() {
		try {
			// 获取分页数据
			regionService.getList(pageBean);
			// 设置忽略的属性：最后忽略的那一项是region中的，为了防止转json过程中死循环
			String json = BosCommonUtils.pageBeanJson(pageBean,
					new String[] { "currentPage", "pageCount", "criteria", "subareas" });
			BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
			BosCommonUtils.getResponseWriter().print(json);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}

		return NONE;
	}

	/**
	 * region文件上传导入数据库
	 * 
	 * @return
	 */
	public String fileUpload() {
		List<Region> regionList = new ArrayList<Region>();
		try {
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
			HSSFSheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				// 表格第一行忽略
				if (row == sheet.getRow(0)) {
					continue;
				}

				// 表格的每一行
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();

				// 利用pinyin4j获取区域简码
				String provinceStr = province.substring(0, province.length() - 1);
				String cityStr = city.substring(0, city.length() - 1);
				String districtStr = district.substring(0, district.length() - 1);

				String[] string = PinYin4jUtils.getHeadByString(provinceStr + cityStr + districtStr);
				String shortcode = StringUtils.join(string);

				// 获取城市简码
				String citycode = PinYin4jUtils.hanziToPinyin(cityStr, "");
				// 创建当前行对应的region对象
				Region region = new Region(id, province, city, district, postcode, shortcode, citycode, null);
				// 封装当前region对象到list集合
				regionList.add(region);
			}

			// 调用service方法将上传的文件存入数据库
			regionService.fileUpload(regionList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return NONE;
	}

	public File getRegionFile() {
		return regionFile;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
}
