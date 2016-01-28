/**
 * 
 */
package cn.hshb.web.common.hibernate;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.engine.QueryParameters;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.TypedValue;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.type.Type;

/**
 * 对Hibernate子查询的扩展。
 * 
 * 参考资料：
 * https://forum.hibernate.org/viewtopic.php?t=973014
 * 
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class SubqueryProjectionCriterion implements Criterion {
	private static final long serialVersionUID = 1776863999431477648L;

	private CriteriaImpl criteriaImpl;
	private QueryParameters params;
	private Type[] types;
	private CriteriaQueryTranslator innerQuery;

	public SubqueryProjectionCriterion(DetachedCriteria dc) {
		this.criteriaImpl = (CriteriaImpl) dc.getExecutableCriteria(null);
	}

	protected Type[] getTypes() {
		return types;
	}

	protected String toLeftSqlString(Criteria criteria, CriteriaQuery outerQuery) {
		return "";
	}

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		return "1";
	}

	private SessionImplementor deriveRootSession(Criteria criteria) {
		if (criteria instanceof CriteriaImpl) {
			return ((CriteriaImpl) criteria).getSession();
		} else if (criteria instanceof CriteriaImpl.Subcriteria) {
			return deriveRootSession(((CriteriaImpl.Subcriteria) criteria).getParent());
		} else {
			return null;
		}
	}

	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		SessionFactoryImplementor factory = criteriaQuery.getFactory();
		createAndSetInnerQuery(criteriaQuery, factory);

		Type[] ppTypes = params.getPositionalParameterTypes();
		Object[] ppValues = params.getPositionalParameterValues();
		TypedValue[] tv = new TypedValue[ppTypes.length];
		for (int i = 0; i < ppTypes.length; i++) {
			tv[i] = new TypedValue(ppTypes[i], ppValues[i], EntityMode.POJO);
		}
		return tv;
	}

	private void createAndSetInnerQuery(CriteriaQuery criteriaQuery, SessionFactoryImplementor factory) {
		if (innerQuery == null) {
			String alias;
			if (this.criteriaImpl.getAlias() == null) {
				alias = criteriaQuery.generateSQLAlias();
			} else {
				alias = this.criteriaImpl.getAlias() + "_";
			}

			innerQuery = new CriteriaQueryTranslator(factory, criteriaImpl, criteriaImpl.getEntityOrClassName(), alias,
					criteriaQuery);

			params = innerQuery.getQueryParameters();
			types = innerQuery.getProjectedTypes();
		}
	}
}
