package caotinging.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * springmvc自定义Date类型参数转换规则
 * Converter<S, T> : S--原始数据类型 T--要转换的数据类型
 * @author caoting
 *
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy:MM/dd HH:mm:ss");
		try {
			return format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
