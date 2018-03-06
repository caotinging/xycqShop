package caotinging.lucene;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
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
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * lucene入门的测试类
 * @author caoting
 *
 */
public class LuceneTest {

	/**
	 * 测试多个默认域名解析查询
	 * @throws Exception 
	 */
	@Test
	public void testMultiFiledQueryParser() throws Exception {
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		
		String[] strs = {"fileName","fileContent"};
		
		//默认到fileName和fileContent中寻找
		MultiFieldQueryParser queryParser = new MultiFieldQueryParser(strs, new IKAnalyzer());
		Query query = queryParser.parse("java and lucene");
		
		LuceneUtils.printResult(indexSearcher, query);
	}
	
	/**
	 * 测试解析查询器（利用查询语法查询）
	 * @throws Exception 
	 */
	@Test
	public void testQueryParser() throws Exception {
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		
		//参数：1.默认采用的域名 2.采用的分析器
		QueryParser queryParser = new QueryParser("fileName", new IKAnalyzer());
		//	 *:*   域：值
		Query query = queryParser.parse("fileName:lucene");
		
		LuceneUtils.printResult(indexSearcher, query);
	}
	
	/**
	 * 测试组合查询索引
	 * @throws Exception 
	 */
	@Test
	public void testBooleanQuery() throws Exception {
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		
		BooleanQuery query = new BooleanQuery();
		//组合条件查询：1.查询条件。2.条件是否必须
		query.add(new TermQuery(new Term("fileName","lucene")), Occur.MUST);
		query.add(NumericRangeQuery.newLongRange("fileSize", 10L, 100L, true, false), Occur.SHOULD);
		
		LuceneUtils.printResult(indexSearcher, query);
	}
	
	/**
	 * 测试根据数值范围查询
	 * @throws Exception 
	 */
	@Test
	public void testNumericRangeQuery() throws Exception {
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		
		//参数：1.域名 2.最小值 3.最大值 4.最小值是否包括 5.最大值是否包括
		Query query = NumericRangeQuery.newLongRange("fileSize", 10L, 100L, true, false);
		
		LuceneUtils.printResult(indexSearcher, query);
	}
	
	/**
	 * 测试查询全部索引MatchAllDocsQuery查询方法
	 * @throws Exception 
	 */
	@Test
	public void testMatchAllDocsQuery() throws Exception {
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		LuceneUtils.printResult(indexSearcher, new MatchAllDocsQuery());
	}
	
	/**
	 * 测试修改索引库：原理就是先删除后添加
	 * @throws Exception 
	 */
	@Test
	public void testModifyIndex() throws Exception {
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		
		Document doc = new Document();
		doc.add(new TextField("fileC", "测试文件1", Store.YES));
		doc.add(new TextField("fileN", "测试文件2", Store.YES));
		
		indexWriter.updateDocument(new Term("fileName", "java"), doc, new IKAnalyzer());
		indexWriter.close();
	}
	
	/**
	 * 测试按条件查询删除
	 * @throws Exception 
	 */
	@Test
	public void testDeleteByQuery() throws Exception {
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		
		Query query = new TermQuery(new Term("fileName", "apache"));
		indexWriter.deleteDocuments(query);
		
		indexWriter.close();
	}
	
	/**
	 * 测试索引的全部删除
	 * @throws Exception
	 */
	@Test
	public void testDeleteAllIndex() throws Exception {
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		indexWriter.deleteAll();
		indexWriter.close();
	}
	
	/**
	 * 查看分析器的分词效果
	 */
	@Test
	public void testTokenStream() throws Exception {
		//创建一个标准分析器对象
		@SuppressWarnings("resource")
		Analyzer analyzer = new IKAnalyzer();
		//获得tokenStream对象
		//第一个参数：域名，可以随便给一个
		//第二个参数：要分析的文本内容
		TokenStream tokenStream = analyzer.tokenStream("test", "心语长情二维表和中国人的羁绊");
		//添加一个引用，可以获得每个关键词
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		//添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		//将指针调整到列表的头部
		tokenStream.reset();
		//遍历关键词列表，通过incrementToken方法判断列表是否结束
		while(tokenStream.incrementToken()) {
			//关键词的起始位置
			System.out.println("start->" + offsetAttribute.startOffset());
			//取关键词
			System.out.println(charTermAttribute);
			//结束位置
			System.out.println("end->" + offsetAttribute.endOffset());
		}
		tokenStream.close();
	}
	
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
		Analyzer analyzer = new IKAnalyzer();
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
