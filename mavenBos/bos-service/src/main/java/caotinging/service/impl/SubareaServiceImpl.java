package caotinging.service.impl;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.ISubareaDao;
import caotinging.domain.Subarea;
import caotinging.service.ISubareaService;
import caotinging.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void saveSubarea(Subarea subarea) {
		subareaDao.save(subarea);
	}

	@Override
	public void getPageList(PageBean<Subarea> pageBean) {
		subareaDao.queryPageBeanList(pageBean);
	}

	@Override
	public void exportAllToXls(HSSFWorkbook workbook) {
		// 查询所有分区数据
		List<Subarea> list = subareaDao.findAll();

		// 创建一个标签页
		HSSFSheet sheet = workbook.createSheet("分区数据");
		// 创建第一行：标题行
		HSSFRow row = sheet.createRow(0);
		// 创建第一行的单元格
		row.createCell(0).setCellValue("分区编号");
		row.createCell(1).setCellValue("开始编号");
		row.createCell(2).setCellValue("结束编号");
		row.createCell(3).setCellValue("位置信息");
		row.createCell(4).setCellValue("省市区");

		// 录入数据
		for (Subarea subarea : list) {
			HSSFRow newRow = sheet.createRow(sheet.getLastRowNum() + 1);
			newRow.createCell(0).setCellValue(subarea.getId());
			newRow.createCell(1).setCellValue(subarea.getStartnum());
			newRow.createCell(2).setCellValue(subarea.getEndnum());
			newRow.createCell(3).setCellValue(subarea.getAddresskey());
			newRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
	}
}
