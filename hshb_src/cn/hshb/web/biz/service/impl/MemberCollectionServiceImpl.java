/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.MemberCollectionMapper;
import cn.hshb.web.biz.mybatis.model.MemberCollection;
import cn.hshb.web.biz.mybatis.model.MemberCollectionExample;
import cn.hshb.web.biz.service.MemberCollectionService;

/**
 * @author Administrator
 *
 */
@Service
public class MemberCollectionServiceImpl implements MemberCollectionService {

	@Autowired
	MemberCollectionMapper memberCollectionMapper;
	
	@Override
	public List<MemberCollection> getCollectionsByMemberId(Integer memberId) {
		MemberCollectionExample example = new MemberCollectionExample();
		example.createCriteria()
			.andMemberIdEqualTo(memberId)
			.andDeleteflagEqualTo(0);
		
		List<MemberCollection> collList = memberCollectionMapper.selectByExample(example);
		return collList;
	}

}
