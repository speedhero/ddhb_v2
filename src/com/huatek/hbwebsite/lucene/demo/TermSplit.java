package com.huatek.hbwebsite.lucene.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import cn.hshb.web.common.util.StringUtil;

public class TermSplit {
	public static void main(String[] args) {
		String str = "对于Lucene4.3开发首先摆在我 们面前的第一个必须要解决的问题， 就是关于中文分词的问题， 因为Lucene毕竟是国外的大牛们开 发的，显然会比较侧重英文文章，不过还好，在Lucene的下载包里同步了SmartCN的分词器针对 中文发行的，每一次Lucene有新的版本发行，这个包同时更新。";
		new TermSplit();
		List<String> list = analyzerCnStr(str);
		for(String s: list)
			System.out.println(s);
		
		List<String> list1 = StringUtil.termSplitForCN(str);
		for(String s: list1)
			System.out.println(s);
	}

	public static List<String> analyzerCnStr(String str) {
		List<String> result = new ArrayList<String>();
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);
		try {
			TokenStream ts = analyzer.tokenStream("field", str);
			CharTermAttribute term = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);
			ts.reset();

			while (ts.incrementToken()) {
				result.add(term.toString());
			}
			ts.end();
			ts.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			analyzer.close();
		}
		return result;
	}
}
