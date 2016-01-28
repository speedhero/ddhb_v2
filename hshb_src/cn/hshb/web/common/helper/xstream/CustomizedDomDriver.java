/**
 * 
 */
package cn.hshb.web.common.helper.xstream;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

/**
 * @author Administrator
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class CustomizedDomDriver extends DomDriver {
	/**
	 * The list that contains the field names that would contain a CDATA in the
	 * xml.
	 */
	private static final List<String> CDATA_FIELDS = new ArrayList<String>();

	static {
		// add cdata field names.eg:cdataField
//		CDATA_FIELDS.add("cdataField");
	}
	
	public void addCDATAField(String fieldName){
		CDATA_FIELDS.add(fieldName);
	}

	public void addCDATAField(List<String> fieldNameList){
		CDATA_FIELDS.addAll(fieldNameList);
	}
	
	public void addCDATAField(String[] fieldNames){
		for(String str: fieldNames)
			addCDATAField(str);
	}

	public void removeCDATAField(String fieldName){
		CDATA_FIELDS.remove(fieldName);
	}

	public void removeCDATAField(List<String> fieldNameList){
		for(String str: fieldNameList)
			removeCDATAField(str);
	}
	public void removeCDATAField(String[] fieldNames){
		for(String str: fieldNames)
			removeCDATAField(str);
	}
	
	
	@Override
	public HierarchicalStreamWriter createWriter(Writer out) {
		return new PrettyPrintWriter(out) {
			boolean cdata = false;

			public void startNode(String name) {
				super.startNode(name);
				cdata = CDATA_FIELDS.contains(name);
			}

			protected void writeText(QuickWriter writer, String text) {
				if (cdata) {
					writer.write("<![CDATA[");
					writer.write(text);
					writer.write("]]>");
				} else {
					writer.write(text);
				}
			}
		};
	}
}
