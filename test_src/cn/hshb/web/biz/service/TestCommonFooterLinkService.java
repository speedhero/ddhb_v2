/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hshb.web.biz.TestBase;
import cn.hshb.web.biz.mybatis.model.CommonFooterLink;

/**
 * @author ShengYoufu
 *
 */
public class TestCommonFooterLinkService extends TestBase {
	private static final Log log = LogFactory.getLog(TestCommonFooterLinkService.class);
	
	@Autowired
	private CommonFooterLinkService commonFooterLinkService = null;
	
	
	 @Before
	 public void setUp() throws Exception {
//		ApplicationContext aCtx = new FileSystemXmlApplicationContext("D:/Project/website/project/ddhb/web/WEB-INF/application-*.xml");
//		CommonFooterLinkService service = (CommonFooterLinkService) aCtx.getBean("commonFooterLinkService");
		//assertNotNull(commonFooterLinkService);
	 }

	 @After
	 public void tearDown() throws Exception {
		 
	 }

	 
	 @Test
	 public void testGetRandomLinksForSale() {
		 List<CommonFooterLink> list = commonFooterLinkService.getRandomLinksForSale(10);
		 for(CommonFooterLink l: list){
			 log.debug(l.toString());
		 }
	}
	 
}
