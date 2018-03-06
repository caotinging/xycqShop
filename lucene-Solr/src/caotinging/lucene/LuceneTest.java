package caotinging.lucene;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

/**
 * lucene入门的测试类
 * @author caoting
 *
 */
public class LuceneTest {

	/**
	 * 测试创建索引库
	 * @throws Exception 
	 */
	@Test
	public void testCreateIndex() throws Exception {
//		第一步：导入jar包。
//		第二步：创建一个indexwriter对象。
//			1）指定索引库的存放位置Directory对象
//			2）指定一个分析器，对文档内容进行分析。
		Directory directory = FSDirectory.open(new File("F:\\files\\data"));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer );
		
		IndexWriter indexWriter = new IndexWriter(directory, config);
//		第二步：创建document对象。(获取文件目录下的每个文件，进行遍历)
		File file = new File("F:\\files\\searchsource");
		File[] files = file.listFiles();
		for (File f : files) {
			Document document = new Document();
			
//		第三步：创建field对象，将field添加到document对象中。
			//文件名称
			String fileName = f.getName();
			Field fileNameField = new TextField("fileName", fileName, Store.YES);
			
			//文件大小
			long fileSize = FileUtils.sizeOf(f);
			Field fileSizeField = new LongField("fileSize", fileSize, Store.YES);
			
			//文件路径
			String filePath = f.getPath();
			Field filePathField = new StoredField("filePath", filePath);
			
			//文件内容
			String fileContent = FileUtils.readFileToString(f);
			Field fileContentField = new TextField("fileContent", fileContent, Store.NO);
			
			document.add(fileNameField);
			document.add(fileSizeField);
			document.add(filePathField);
			document.add(fileContentField);
			
//		第四步：使用indexwriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库。
			indexWriter.addDocument(document);
		}
		
//		第五步：关闭IndexWriter对象。
		indexWriter.close();
	}
}
