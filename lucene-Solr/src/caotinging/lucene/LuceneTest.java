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
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
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
	 * 测试查询索引
	 * @throws Exception 
	 */
	@Test
	public void testQueryIndex() throws Exception {
//		第一步：创建一个Directory对象，也就是索引库存放的位置。
		Directory directory = FSDirectory.open(new File("F:\\files\\data"));
//		第二步：创建一个indexReader对象，需要指定Directory对象。
		IndexReader indexReader = DirectoryReader.open(directory);
//		第三步：创建一个indexsearcher对象，需要指定IndexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//		第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。Term("域名","域值")
		TermQuery termQuery = new TermQuery(new Term("fileName", "apache"));
//		第五步：执行查询。（这里的意思是根据查询条件查询评分最高的10条记录）过滤策略和评分策略都可以通过重载方法自定义
		TopDocs topDocs = indexSearcher.search(termQuery, 10);
//		第六步：返回查询结果。遍历查询结果并输出。
		ScoreDoc[] docs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : docs) {
			//拿到查询出来的索引的id
			int doc = scoreDoc.doc;
			//根据id获取到相对应的文档
			Document document = indexSearcher.doc(doc);
			// 文件名称
			String fileName = document.get("fileName");
			System.out.println(fileName);
			// 文件内容
			String fileContent = document.get("fileContent");
			System.out.println(fileContent);
			// 文件大小
			String fileSize = document.get("fileSize");
			System.out.println(fileSize);
			// 文件路径
			String filePath = document.get("filePath");
			System.out.println(filePath);
			System.out.println("-------------------------------------------------");
		}
//		第七步：关闭IndexReader对象
		indexReader.close();
	}
	
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
