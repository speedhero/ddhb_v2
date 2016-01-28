package com.huatek.hbwebsite.service;

import com.huatek.base.entity.BaseEntity;
import com.huatek.base.service.BaseService;

import java.util.List;

import org.hibernate.Criteria;

public interface BaseServiceTo extends BaseService {
	/**
	 * 根据记录ID数组查询数据
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	List<?> findDatasByIds(Class<? extends BaseEntity> entityClass, String[] ids);
	/**
	 * 根据记录ID查询数据
	 * @param entityClass
	 * @param id
	 * @return
	 */
	Object getObjectById(Class<? extends BaseEntity> entityClass, String id);

	/**
	 * 根据Criteria查询记录数
	 * @param criteria
	 * @return
	 */
	Long getTotalRowCount(Criteria criteria);
}
