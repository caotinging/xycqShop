package caotinging.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * lucene工具类
 * @author caoting
 *
 */
public class LuceneUtils {
	
	public static void printResult(IndexSearcher indexSearcher, Query query) throws Exception {
		TopDocs topDocs = indexSearcher.search(query, 10);
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
	}

	public static IndexWriter getIndexWriter() throws IOException {
		Directory directory = FSDirectory.open(new File("F:\\files\\data"));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer );
		
		return new IndexWriter(directory, config);
	}
	
	public static IndexReader getIndexReader() throws Exception {
		Directory directory = FSDirectory.open(new File("F:\\files\\data"));
		return DirectoryReader.open(directory);
	}
	
	public static IndexSearcher getIndexSearcher() throws Exception {
		Directory directory = FSDirectory.open(new File("F:\\files\\data"));
		IndexReader indexReader = DirectoryReader.open(directory);
		return new IndexSearcher(indexReader);
	}
}
