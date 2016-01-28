/**
 * 
 */
package cn.hshb.web.partner.baidu.common;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.web.servlet.view.AbstractView;

import cn.hshb.web.common.exception.HshbException;
import cn.hshb.web.partner.baidu.entity.houseSecond.Urlset;

/**
 * 自定义XML视图解析器
 * 
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class BaiduHouseXmlView extends AbstractView {
	private static final String CONTENT_TYPE = "application/xml";

	@Override
	public String getContentType() {
		return CONTENT_TYPE;
	}

	/**
	 * The model attribute to be set by the controller.
	 */
	private String modelAttributeName;

	/**
	 * Accessor
	 *
	 * @return
	 */
	public String getModelAttributeName() {
		return modelAttributeName;
	}

	/**
	 * Mutator
	 *
	 * @param modelAttributeName
	 */
	public void setModelAttributeName(String modelAttributeName) {
		this.modelAttributeName = modelAttributeName;
	}

	/**
	 * Render the XML object response which will be sent to the requested client.
	 *
	 * @param objectMap
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Override
	protected void renderMergedOutputModel(Map objectMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter writer = null;
		Object xmlObject = objectMap.get(modelAttributeName);
		if (xmlObject == null) { throw new HshbException("Required model attribute not found: " + modelAttributeName); }

		try {
			response.setContentType(CONTENT_TYPE);
			writer = response.getWriter();
//			Marshaller marshaller = JAXBContextManager.getInstance().createMarshaller();
			// marshal the xmlObject through writer
//			marshaller.marshal(xmlObject, writer);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Urlset.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(xmlObject, writer);

		} catch (JAXBException exc) {
			logger.error("JAXB Exception while rendering XML response to client: " + request.getRemoteAddr(), exc);
			throw new HshbException("JAXB Exception", exc);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
				writer = null;
			}
		}
	}

}
