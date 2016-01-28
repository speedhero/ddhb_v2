package com.huatek.hbwebsite.service;

import com.huatek.base.entity.BaseEntity;
import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.hbwebsite.service.BaseServiceTo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;

public class BaseServiceToImpl extends BaseServiceImpl implements BaseServiceTo {
	private static final Log log = LogFactory.getLog(BaseServiceToImpl.class);
	/**
	 * 根据记录ID数组查询数据
	 */
	public List<?> findDatasByIds(Class<? extends BaseEntity> entityClass, String[] ids) {
		StringBuffer param = new StringBuffer("");
		for(String id: ids)
			param.append(",").append("?");
		if(param.length()>0)
			param.deleteCharAt(0);
		return hibernateTemplate.find("from " + entityClass.getName() + " t where t.id in (" + param + ")", ids);
	}

	/**
	 * 根据记录ID查询数据
	 */
	public Object getObjectById(Class<? extends BaseEntity> entityClass, String id) {
		BaseEntity baseEntity = (BaseEntity) hibernateTemplate.get(entityClass, id);
		if (baseEntity == null) {
			log.error("数据不存在或已删除。class: " + entityClass.getClass().getCanonicalName() + ", id: " + id);
			throw new BusinessRuntimeException("error.data.deleted: " + entityClass.getClass().getCanonicalName() + ", id: " + id);
		} else {
			return baseEntity;
		}
	}
	
	/**
	 * 根据Criteria查询总记录数
	 * @param criteria
	 * @return
	 */
	public Long getTotalRowCount(Criteria criteria) {
		// criteria.setProjection(null).setResultTransformer(Criteria.ROOT_ENTITY);
		criteria.setProjection(null).setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		Long totalRow = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();

		return totalRow;
	}
}
