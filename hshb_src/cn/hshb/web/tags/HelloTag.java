/**
 * 
 */
package cn.hshb.web.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Administrator
 *
 */
public class HelloTag extends SimpleTagSupport {
	private String message;

	public void setMessage(String msg) {
		this.message = msg;
	}

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		if (message != null) {
			JspWriter out = getJspContext().getOut();
			out.println(message);
		} else {
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
}
