/**
 * 
 */
package cn.hshb.web.common.hibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.sql.Select;
import org.hibernate.type.Type;

/**
 *  对Hibernate子查询的扩展。
 * 参考资料：
 * https://forum.hibernate.org/viewtopic.php?t=973014
 * 
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class SubqueryProjection implements Projection {

  private CriteriaImpl subquery;
  private CriteriaQueryTranslator innerQuery;
  
  public SubqueryProjection(DetachedCriteria subquery) {
     this.subquery = (CriteriaImpl) subquery.getExecutableCriteria(null);
  }

  public String[] getAliases() {
     return new String[1];
  }

  public String[] getColumnAliases(int loc) {
     return new String[] { "y" + loc + "_" };
  }

  public String[] getColumnAliases(String alias, int loc) {
     return null;
  }

  public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
     if (innerQuery == null)
        innerQuery = buildInnerQuery(criteria, criteriaQuery);
     
     return innerQuery.getProjectedTypes();
  }

  public Type[] getTypes(String alias, Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
     return null;
  }

  public boolean isGrouped() {
     return false;
  }

  public String toGroupSqlString(Criteria arg0, CriteriaQuery arg1) throws HibernateException {
     throw new UnsupportedOperationException("not a grouping projection");
  }

  public String toSqlString(Criteria criteria, int position, CriteriaQuery criteriaQuery) throws HibernateException {
     final SessionImplementor session = ( (CriteriaImpl) criteria ).getSession(); //ugly!
     final SessionFactoryImplementor factory = session.getFactory();
     
     final OuterJoinLoadable persister = (OuterJoinLoadable) factory.getEntityPersister( subquery.getEntityOrClassName() );
     
     if (innerQuery == null)
        innerQuery = buildInnerQuery(criteria, criteriaQuery);
     
     String sql = new Select(factory.getDialect())
        .setWhereClause(innerQuery.getWhereCondition())
        .setGroupByClause(innerQuery.getGroupBy())
        .setSelectClause(innerQuery.getSelect())
        .setFromClause(
              persister.fromTableFragment(innerQuery.getRootSQLALias()) +   
              persister.fromJoinFragment(innerQuery.getRootSQLALias(), true, false)
           )
        .toStatementString();

     final StringBuffer buf = new StringBuffer();
     return buf.append('(').append(sql).append(") as y")
        .append(position).append('_').toString();
  }

  private CriteriaQueryTranslator buildInnerQuery(Criteria criteria, CriteriaQuery criteriaQuery) {
     SessionImplementor session = ((CriteriaImpl) criteria).getSession(); //ugly!
     SessionFactoryImplementor factory = session.getFactory();
     
     CriteriaQueryTranslator innerQuery = new CriteriaQueryTranslator(
           factory,
           subquery,
           subquery.getEntityOrClassName(), //implicit polymorphism not supported (would need a union)
           criteriaQuery.generateSQLAlias(),
           criteriaQuery
        );
     return innerQuery;
  }
}
