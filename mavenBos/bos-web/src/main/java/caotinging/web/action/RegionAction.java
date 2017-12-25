package caotinging.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Region;
import caotinging.service.IRegionService;
import caotinging.utils.BosCommonUtils;
import caotinging.utils.PinYin4jUtils;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	private static final long serialVersionUID = 1L;
	
	private File regionFile;
	@Autowired
	private IRegionService regionService;
	
	/**
	 * 区域列表查询
	 * @return
	 */
	public String getList() {
		try {
			//获取分页数据
			regionService.getList(pageBean);
			//设置忽略的属性：最后忽略的那一项是region中的，为了防止转json过程中死循环
			String json = BosCommonUtils.pageBeanJson(pageBean, new String[]{"currentPage","pageCount","criteria","subareas"});
			BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
			BosCommonUtils.getResponseWriter().print(json);
		}catch(Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		
		return NONE;
	}
	
	/**
	 * region文件上传导入数据库
	 * @return
	 */
	public String fileUpload() {
		List<Region> regionList = new ArrayList<Region>();
		try {
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			for(Row row : sheet) {
				//表格第一行忽略
				if(row == sheet.getRow(0)) {
					continue;
				}
				
				//表格的每一行
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				
				//利用pinyin4j获取区域简码
				province = province.substring(0,province.length()-1);
				city = city.substring(0,city.length()-1);
				district = district.substring(0,district.length()-1);
				
				String[] string = PinYin4jUtils.getHeadByString(province+city+district);
				String shortcode = StringUtils.join(string);
				
				//获取城市简码
				String citycode = PinYin4jUtils.hanziToPinyin(city, "");
				//创建当前行对应的region对象
				Region region = new Region(id, province, city, district, postcode, shortcode, citycode, null);
				//封装当前region对象到list集合
				regionList.add(region);
			}
			
			//调用service方法将上传的文件存入数据库
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
}
