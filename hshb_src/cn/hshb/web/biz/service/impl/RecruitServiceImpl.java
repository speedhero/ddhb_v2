/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;

import cn.hshb.web.biz.mybatis.dao.RecruitCandidateMapper;
import cn.hshb.web.biz.mybatis.dao.RecruitPositionMapper;
import cn.hshb.web.biz.mybatis.dao.RecruitPositionTypeMapper;
import cn.hshb.web.biz.mybatis.model.RecruitCandidate;
import cn.hshb.web.biz.mybatis.model.RecruitCandidateExample;
import cn.hshb.web.biz.mybatis.model.RecruitPosition;
import cn.hshb.web.biz.mybatis.model.RecruitPositionExample;
import cn.hshb.web.biz.mybatis.model.RecruitPositionKey;
import cn.hshb.web.biz.mybatis.model.RecruitPositionType;
import cn.hshb.web.biz.mybatis.model.RecruitPositionTypeExample;
import cn.hshb.web.biz.service.RecruitService;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class RecruitServiceImpl implements RecruitService {
	private static final Log log = LogFactory.getLog(RecruitServiceImpl.class);
	private static final Integer PAGE_SIZE = 10;

	@Autowired
	private RecruitPositionTypeMapper recruitPositionTypeMapper;
	
	@Autowired
	private RecruitPositionMapper recruitPositionMapper;
	
	@Autowired
	private RecruitCandidateMapper recruitCandidateMapper;
	
	@Override
	public List<RecruitPositionType> getAllRecruitPositionType() {
		RecruitPositionTypeExample example = new RecruitPositionTypeExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		return recruitPositionTypeMapper.selectByExample(example);
	}

	@Override
	public List<RecruitPosition> getRecruitPositionByType(Integer typeId, Integer pageSize, Integer pageNum){
		Integer pgSize = pageSize!=null && pageSize>0 ? pageSize : PAGE_SIZE;
		Integer pgNum = pageNum != null && pageNum>0 ?  pageNum : 1;
		PageHelper.startPage(pgNum, pgSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeId", typeId);
		return recruitPositionMapper.selectRecruitPositionMapperByTypeId(map);
	}

	@Override
	public List<RecruitPosition> getRecruitPositionByType(Integer typeId,
			Integer pageSize, String pageNumStr) {
		int pageNum = 1;
		if(StringUtil.isNotEmpty(pageNumStr)){
			Matcher ma = Pattern.compile("n([\\d]+)").matcher(pageNumStr);
			while(ma.find()){
				pageNum = Integer.parseInt(ma.group(1));
			}
		}
		return getRecruitPositionByType(typeId, pageSize, pageNum);
	}
	@Override
	public RecruitPosition getPositionDetailByPositionId(Integer positionId) {
//		RecruitPositionExample example = new RecruitPositionExample();
//		example.createCriteria()
//			.andIdEqualTo(positionId)
//			.andIspublishedEqualTo(1)
//			.andDeleteflagEqualTo(0);
		
		RecruitPosition rp = recruitPositionMapper.selectRecruitPositionMapperById(positionId);
		return rp!=null ? rp : new RecruitPosition();
	}

	@Override
	public Boolean getIsExist(Integer positionId, String name, String telephone) {
		RecruitCandidateExample example = new RecruitCandidateExample();
		example.createCriteria()
			.andApplypositionEqualTo(positionId)
			.andNameEqualTo(name)
			.andTelephoneEqualTo(telephone)
			.andDeleteflagEqualTo(0);
		
		int ret = recruitCandidateMapper.countByExample(example);
		return ret<=0;  //TODO: 这里原代码逻辑就是小于等于0则返回 true
	}
	
	@Override
	public Boolean save(RecruitCandidate record){
		Integer ret = recruitCandidateMapper.insert(record);
		return ret>0;
	}
}
