package caotinging.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 用于解耦的工厂类
 * @author caoting
 *
 */
public class BeanFactory {

	public static Object getBean(String id) {
		try {
			String path = BeanFactory.class.getClassLoader().getResource("beanfactory.xml").getPath();
			SAXReader reader = new SAXReader();

			Document doc = reader.read(path);
			Element element = (Element) doc.selectSingleNode("//bean[@id='"+id+"']");
			
			String className = element.attributeValue("class");
			Class<?> clazz = Class.forName(className);
			
			Object newInstance = clazz.newInstance();
			
			return newInstance;
		} catch (DocumentException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
