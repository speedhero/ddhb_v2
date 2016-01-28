package com.huatek.hbwebsite.lucene.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	private String endStr;

	public Indexer(String endStr) {
		this.endStr = endStr;
	}

	public int index(File indexDir, File dataDir) throws IOException {
		if (dataDir.exists() && dataDir.isDirectory()) {
			SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_46, analyzer);
			FSDirectory directory = FSDirectory.open(indexDir);
			if (IndexWriter.isLocked(directory)) {
				IndexWriter.unlock(directory);
			}

			IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
			writer.deleteAll();
			this.indexDirectory(writer, dataDir);
			int numIndexed = writer.numDocs();
			writer.close();
			return numIndexed;
		} else {
			throw new IOException(dataDir + " does not exist or is not a directory");
		}
	}

	public void indexDirectory(IndexWriter writer, File dir) throws IOException {
		File[] files = dir.listFiles();
		File[] var7 = files;
		int var6 = files.length;

		for (int var5 = 0; var5 < var6; ++var5) {
			File file = var7[var5];
			if (file.isDirectory()) {
				this.indexDirectory(writer, file);
			} else if (file.getName().endsWith(this.endStr)) {
				this.indexFile(writer, file);
			}
		}

	}

	public void indexFile(IndexWriter writer, File f) throws IOException {
		if (!f.isHidden() && f.exists() && f.canRead()) {
			Document doc = new Document();
			String content = this.getTXT(f, "GBK");
			doc.add(new TextField("filePath", f.getCanonicalPath(), Store.YES));
			doc.add(new TextField("content", content, Store.YES));
			writer.addDocument(doc);
			writer.commit();
		}
	}

	public String getTXT(File file, String charset) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		new String();

		String line;
		String result;
		for (result = new String(); (line = reader.readLine()) != null; result = result + line) {
			;
		}

		reader.close();
		return result;
	}

	public static void main(String[] args) {
		Indexer indexer = new Indexer(".txt");

		try {
			File indexDir = new File("d:\\lucene\\index");
			File dataDir = new File("d:\\lucene\\file");
			indexer.index(indexDir, dataDir);
		} catch (IOException var5) {
			;
		}

	}
}
