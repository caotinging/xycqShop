package caotinging.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.junit.Test;

import caotinging.utils.PinYin4jUtils;

public class PoiTest {
	
	//测试pinyin4j
	//@Test
	public void fun2() {
		//湖南省 长沙市 星沙区 ---->简码：HNCSXS
		String provice = "湖南省";
		String city = "长沙市";
		String district = "星沙区";
		
		//字符串截断
		provice = provice.substring(0,provice.length()-1);
		city = city.substring(0,city.length()-1);
		district = district.substring(0,district.length()-1);
		
		String[] string = PinYin4jUtils.getHeadByString(provice+city+district);
		
		String join = StringUtils.join(string);
		
		System.out.println(join);
	}

	//@Test
	public void fun1() throws FileNotFoundException, IOException {
		String filePath = "G:\\区域导入测试数据.xls";
		
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			for(Cell cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
}
