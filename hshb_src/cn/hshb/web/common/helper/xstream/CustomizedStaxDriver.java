/**
 * 
 */
package cn.hshb.web.common.helper.xstream;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.StaxWriter;

/**
 * 自定义StaxDriver，实现
 * 
 * @author Administrator
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class CustomizedStaxDriver extends StaxDriver {

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
	

	public CustomizedStaxDriver() {
		super();
	}

	@Override
	public StaxWriter createStaxWriter(XMLStreamWriter out, boolean writeStartEndDocument) throws XMLStreamException {
		final XMLStreamWriter writer = out;
		return new StaxWriter(getQnameMap(), out, writeStartEndDocument, isRepairingNamespace()) {
			boolean cdata = false;

			@Override
			public void startNode(String name) {
				super.startNode(name);
				cdata = CDATA_FIELDS.contains(name);
			}

			@Override
			public void setValue(String value) {
				if (cdata) {
					try {
						writer.writeCData(value);
					} catch (XMLStreamException e) {
						e.printStackTrace();
					}
				} else {
					super.setValue(value);
				}
			}
		};
	}
}
