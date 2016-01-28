/**
 * 
 */
package cn.hshb.web.biz;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hshb.web.biz.mybatis.model.CommonFooterLink;
import cn.hshb.web.biz.service.CommonFooterLinkService;

/**
 * @author ShengYoufu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  {
	"file:web/WEB-INF/application-authority.xml",
	"file:web/WEB-INF/application-base.xml",
	"file:web/WEB-INF/application-cache.xml",
	"file:web/WEB-INF/application-db.xml",
	"file:web/WEB-INF/application-ddhb-config.xml",
	"file:web/WEB-INF/application-dictionary.xml",
	"file:web/WEB-INF/application-hshb-service.xml",
	"file:web/WEB-INF/application-mail.xml",
	"file:web/WEB-INF/application-quartz-task.xml",
	"file:web/WEB-INF/framework-servlet.xml"
  } 
)
public class TestBase {
	protected Log logger = LogFactory.getLog(TestBase.class);
	
	 @Resource protected ApplicationContext m_oApplicationContext;
	 
	@Autowired
	private CommonFooterLinkService commonFooterLinkService = null;

	/**
	 * 一些公用的“初始化”代码
	 */
	@Before
	public void before(){
		
	}
	

	 @Test
	 public void testGetRandomLinksForSale() {
		 List<CommonFooterLink> list = commonFooterLinkService.getRandomLinksForSale(10);
		 for(CommonFooterLink l: list){
			 System.out.println(l.toString());
		 }
	}	
}
