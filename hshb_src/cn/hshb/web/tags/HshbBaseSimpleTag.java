/**
 * 
 */
package cn.hshb.web.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 豪世华邦自定义标签基类，其他标签必须继承该标签
 * 
 * @author Administrator
 *
 */
public class HshbBaseSimpleTag extends SimpleTagSupport {
	protected void write(String str) throws JspException{
		PageContext pageContext = (PageContext) getJspContext(); 
        JspWriter out = pageContext.getOut(); 
		try{
			out.print(str);
		} catch (Exception ex) { 
			throw new JspException(ex);
        } 
	}
	
	protected PageContext getPageContext(){
		return (PageContext) getJspContext();
	}
	
	protected HttpServletRequest getRequest(){
		return (HttpServletRequest)getPageContext().getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return (HttpServletResponse)getPageContext().getResponse();
	}
	
	protected Boolean containsAttr(String attrName){
		return !(getRequest().getAttribute(attrName) == null);
	}
	
}
