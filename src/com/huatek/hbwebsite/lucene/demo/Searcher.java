package com.huatek.hbwebsite.lucene.demo;

import java.io.File;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {
	private File indexDir;

	public Searcher(String IndexDir) {
		this.indexDir = new File(IndexDir);
	}

	public void search(String keywords) throws Exception {
		FSDirectory fsDir = FSDirectory.open(this.indexDir);
		DirectoryReader reader = DirectoryReader.open(fsDir);
		IndexSearcher is = new IndexSearcher(reader);
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);
		QueryParser queryParser = new QueryParser(Version.LUCENE_46, "content", analyzer);
		Query query = queryParser.parse(keywords);
		TopDocs docs = is.search(query, 6);
		ScoreDoc[] scoreDoc = docs.scoreDocs;
		System.err.println("Found " + docs.totalHits + " document(s) that matched query \'" + keywords + "\'.");

		for (int i = 0; i < scoreDoc.length; ++i) {
			reader.document(scoreDoc[i].doc);
		}

		reader.close();
	}

	public static void main(String[] args) {
		Searcher searcher = new Searcher("d:\\lucene\\index");

		try {
			searcher.search("工具 效果");
		} catch (Exception var3) {
			;
		}

	}
}
