/**
 * 
 */
package cn.hshb.web.common.helper.xstream;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

/**
 * @author Administrator
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class CustomeXStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomizedDomDriver testDomeDriver = new CustomizedDomDriver();
		testDomeDriver.addCDATAField("name");
		testDomeDriver.addCDATAField("content");

		XStream xstream = new XStream(testDomeDriver);
		 
		TestObject obj = new TestObject();
		obj.setAge(10);
		obj.setName("张三");
		obj.setContent(" 希腊为还债向德国讨1620亿欧元二战赔款 德国回应");
		String str = xstream.toXML(obj);
		System.out.println(str);
		
		List<TestObject> objList = new ArrayList<TestObject>();
		for(int ii=0; ii<10; ii++){
			obj = new TestObject();
			obj.setAge(10);
			obj.setName("张三"+String.valueOf(ii));
			obj.setContent("希腊为还债向德国讨1620亿欧元二战赔款 德国回应["+String.valueOf(ii)+"]");
			objList.add(obj);
		}
		str = xstream.toXML(objList);
		System.out.println(str);
	}

}
