/**
 * 
 */
package cn.hshb.web.partner.baidu.service;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseSecondService;

import cn.hshb.web.partner.baidu.entity.houseSecond.TUrl;
import cn.hshb.web.partner.baidu.entity.houseSecond.Urlset;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class TransferToBaiduScheduleServiceImpl extends BaseServiceImpl implements TransferToBaiduScheduleService {
	private static final Log log = LogFactory.getLog(TransferToBaiduScheduleServiceImpl.class);
	
	private static final String QUERY_HOUSE_SECOND_ADD = "UPDATE recruit_candidate set deleteflag = 1 where DATE_ADD(applytime, INTERVAL 60 DAY) < now() and status = 1 and deleteflag = 0";
	private static final String QUERY_HOUSE_SECOND_MOD = "UPDATE recruit_candidate set deleteflag = 1 where DATE_ADD(applytime, INTERVAL 60 DAY) < now() and status = 1 and deleteflag = 0";
	private static final String QUERY_HOUSE_SECOND_DEL = "UPDATE recruit_candidate set deleteflag = 1 where DATE_ADD(applytime, INTERVAL 60 DAY) < now() and status = 1 and deleteflag = 0";
	private static final String QUERY_HOUSE_RENT_ADD = "delete from house_second_hand_house where DATE_ADD(lastmodified, INTERVAL 30 DAY) < now() and deleteflag = 1;";
	private static final String QUERY_HOUSE_RENT_MOD = "delete from house_second_hand_house where DATE_ADD(lastmodified, INTERVAL 30 DAY) < now() and deleteflag = 1;";
	private static final String QUERY_HOUSE_RENT_DEL = "delete from house_second_hand_house where DATE_ADD(lastmodified, INTERVAL 30 DAY) < now() and deleteflag = 1;";
	private static final String QUERY_COMMUNITY = "select * from house_rent_house where DATE_ADD(lastmodified, INTERVAL 30 DAY) < now() and deleteflag = 1;";

	@Autowired
	HouseSecondService houseSecondService;
	
	public void transferHouseSecondAdd(){
		CutPageBean pBean = new CutPageBean();
		//暂不分页
//		pBean.setCurrentPage(0);
//		pBean.setPageSize(100);

		String totalRowsHsql = " select count(t) from house_second_hand_house t ";
		String resultSql = " from HouseSecond t ";

		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(HouseSecond.class, "h");
		List<HouseSecond> houeList = (List<HouseSecond>)criteria.list();
		Urlset urlset = new Urlset();
		for(HouseSecond house: houeList){
			TUrl url = TUrl.fromHouse(house);
			urlset.getUrl().add(url);
		}
		try {
			String xml = urlset.toXML();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void transferHouseSecondMod(){
		
	}
	
	public void transferHouseSecondDel(){
		
	}
}
