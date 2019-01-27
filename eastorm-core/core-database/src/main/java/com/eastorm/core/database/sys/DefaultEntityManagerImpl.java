/**
 *DefaultEntityManager.java
	Description:
 */
package com.eastorm.core.database.sys;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.internal.QueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-11-3
 */
public class DefaultEntityManagerImpl implements DefaultEntityManager {
    
	 private EntityManager em;

	 private Query getAllCountQuery(String jpql){
		Query allCountQry=null;
		//1.查询所有记录总和
		if(!jpql.toUpperCase().startsWith("FROM")){
			 allCountQry=em.createQuery("select count(*) "+jpql.substring(jpql.toLowerCase().indexOf("from")));
		}else{
			allCountQry=em.createQuery("select count(*) "+jpql);
		}
		return allCountQry;
	 }
	/*查询（分页）参数map
	 * add by penkee@163.com
	 */
	@Override
	public Page queryPageByMap(Pageable page, String jpql,Map<String, Object> map) {
		Query allCountQry=getAllCountQuery(jpql);
		setParam(allCountQry,map);
		long all=(Long)allCountQry.getSingleResult();
		//2.查询当前分页的列表
		Query  listQry=em.createQuery(jpql);
		setParam(listQry,map);
		List list=listQry.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
		Page p=new PageImpl(list, page, all);
		return p;
	}

	/* 查询（分页）参数随意
	 * add by penkee@163.com
	 */
	@Override
	public Page queryPage(Pageable page, String jpql, Object... param) {
		//1.查询所有记录总和
		Query allCountQry=getAllCountQuery(jpql);
		setParam(allCountQry,param);
		long all=(Long)allCountQry.getSingleResult();
		//2.查询当前分页的列表
		Query  listQry=em.createQuery(jpql);
		setParam(listQry,param);
		List list=listQry.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
		Page p=new PageImpl(list, page, all);
		return p;
	}

	/*查询（分页）参数map
	 * add by penkee@163.com
	 */
	@Override
	public Page queryPageNativeByMap(Pageable page, String sql,Map<String, Object> map) {
		//1.查询所有记录总和
		String countSql=new String("select count(*) from ("+sql+") a");
		Query  allCountQry=em.createNativeQuery(countSql);
		setParam(allCountQry,map);
		long all=((BigInteger)allCountQry.getSingleResult()).intValue();
		//2.查询当前分页的列表
		Query  listQry=em.createNativeQuery(sql);
		setParam(listQry,map);
		List<Object[]> list=listQry.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
		Page<Object[]> p=new PageImpl<Object[]>(list, page, all);
		return p;
	}

	/* 查询（分页）参数随意
	 * add by penkee@163.com
	 */
	@Override
	public Page<Object[]> queryPageNative(Pageable page, String sql, Object... param) {
		//1.查询所有记录总和
		String countSql=new String("select count(*) from ("+sql+") a");
		Query  allCountQry=em.createNativeQuery(countSql);
		setParam(allCountQry,param);
		long all=(Long)allCountQry.getSingleResult();
		//2.查询当前分页的列表
		Query  listQry=em.createNativeQuery(sql);
		setParam(listQry,param);
		List<Object[]> list=listQry.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
		Page<Object[]> p=new PageImpl<Object[]>(list, page, all);
		return p;
	}
	

	/*查询（分页）参数map
	 * add by penkee@163.com
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Map<String, Object>> queryPageMapNativeByMap(Pageable page, String sql,Map<String, Object> map) {
		//1.查询所有记录总和
		String countSql=new String("select count(*) from ("+sql+") a");
		Query  allCountQry=em.createNativeQuery(countSql);
		setParam(allCountQry,map);
		long all=((BigInteger)allCountQry.getSingleResult()).intValue();
		//2.查询当前分页的列表
		Query  listQry=em.createNativeQuery(sql);
		setParam(listQry,map);
		
		SQLQuery nativeQuery= listQry.unwrap(SQLQuery.class);
		nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String, Object>> list=listQry.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
		Page<Map<String, Object>> p=new PageImpl<Map<String, Object>>(list, page, all);
		return p;
	}
	
	/*查询（分页）参数map
	 * add by penkee@163.com
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Map<String, Object>> queryPageMapNativeByMap(Pageable page, String countSql, String sql,Map<String, Object> map) {
		//1.查询所有记录总和
		long all=0;
		if(countSql!=null){
			Query  allCountQry=em.createNativeQuery(countSql);
			setParam(allCountQry,map);
			all=((BigInteger)allCountQry.getSingleResult()).intValue();
		}
		//2.查询当前分页的列表
		Query  listQry=em.createNativeQuery(sql);
		setParam(listQry,map);
		
		SQLQuery nativeQuery= listQry.unwrap(SQLQuery.class);
		nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String, Object>> list=listQry.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
		Page<Map<String, Object>> p=new PageImpl<Map<String, Object>>(list, page, all);
		return p;
	}
	
	/*命名查询map
	 * add by penkee@163.com
	 */
	@Override
	public List queryNamedByMap(String name, Map<String, Object> map) {
		Query  listQry=em.createNamedQuery(name);
		setParam(listQry,map);
		return listQry.getResultList();
	}

	/*  命名查询参数随意
	 * add by penkee@163.com
	 */
	@Override
	public List queryNamed(String name, Object... param) {
		Query  listQry=em.createNamedQuery(name);
		setParam(listQry,param);
		return listQry.getResultList();
	}

	/*  命名查询参数随意
	 * add by penkee@163.com
	 */
	@Override
	public List<Map<String,Object>> queryMapNamed(String name, Object... param) {
		Query  listQry=em.createNamedQuery(name);
		setParam(listQry,param);
		
		QueryImpl sqlQuery= listQry.unwrap(QueryImpl.class);
		sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list=listQry.getResultList();
		return list;
	}
	
	/*直接传入jpql查询,参数map
	 * add by penkee@163.com
	 */
	@Override
	public List query(String jpql, Map<String, Object> map) {
		Query  listQry=em.createQuery(jpql);
		setParam(listQry,map);
		return listQry.getResultList();
	}

	/*直接传入jpql查询,参数随意
	 * add by penkee@163.com
	 */
	@Override
	public List query(String jpql, Object... param) {
		Query  listQry=em.createQuery(jpql);
		setParam(listQry,param);
		return listQry.getResultList();
	}

	/* 命名查询一条记录,map
	 * add by penkee@163.com
	 */
	@Override
	public Object queryNamedOneByMap(String name, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getListFirst(queryNamedByMap(name,map));
	}

	/*命名查询一条记录,参数随意
	 * add by penkee@163.com
	 */
	@Override
	public Object queryNamedOne(String name, Object... param) {
		// TODO Auto-generated method stub
		return getListFirst(queryNamed(name,param));
	}

	/* 直接传入jpql查询一条记录,参数map
	 * add by penkee@163.com
	 */
	@Override
	public Object queryOne(String sql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getListFirst(query(sql,map));
	}

	/* 直接传入jpql查询一条记录,参数随意
	 * add by penkee@163.com
	 */
	@Override
	public Object queryOne(String jpql, Object... param) {
		// TODO Auto-generated method stub
		return getListFirst(query(jpql,param));
	}

	/**
	 * 获取第一条记录
	 * @param list
	 * @return
	 * add by penkee@163.com
	 */
	private Object getListFirst(List list) {
		try {
			return list.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Map<String,Object>> queryListNativeByMap(String sql,Map<String, Object> param) {
		Query  listQry=em.createNativeQuery(sql);
		setParam(listQry,param);
		SQLQuery nativeQuery= listQry.unwrap(SQLQuery.class);
		nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list=listQry.getResultList();
		return list;
	}
	/**
	 * 转换成map，还可转换成实体，要有构造函数，参数是全部属性，和一个无参构造即可
	 */
	@Override
	public List<Map<String,Object>> queryListNative(String sql, Object... param) {
		Query  listQry=em.createNativeQuery(sql);

		setParam(listQry,param);

		SQLQuery nativeQuery= listQry.unwrap(SQLQuery.class);
		nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list=listQry.getResultList();
		return list;
	}

	/*************************UPDATE OPERATION************************************************************************/
	/*  更新和删除操作
	 * add by penkee@163.com
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateByMap(String jpql, Map<String, Object> map) {
		Query  listQry=em.createQuery(jpql);
		setParam(listQry,map);
		return listQry.executeUpdate();
	}

	/*  更新和删除操作
	 * add by penkee@163.com
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int update(String jpql, Object... param) {
		Query  listQry=em.createQuery(jpql);
		setParam(listQry,param);
		return listQry.executeUpdate();
	}
	/*  更新和删除操作
	 * add by penkee@163.com
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateNamedByMap(String name, Map<String, Object> map) {
		Query  listQry=em.createNamedQuery(name);
		setParam(listQry,map);
		return listQry.executeUpdate();
	}

	/*  更新和删除操作
	 * add by penkee@163.com
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateNamed(String name, Object... param) {
		Query  listQry=em.createNamedQuery(name);
		setParam(listQry,param);
		return listQry.executeUpdate();
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateNative(String sql, Object... param) {
		Query  listQry=em.createNativeQuery(sql);
		setParam(listQry,param);
		return listQry.executeUpdate();
	}
	
	/****************get and set************************************************/
	/**
	 * 传入map设置参数
	 * @param query
	 * @param map
	 * add by penkee@163.com
	 */
	public void setParam(Query query,Map<String, Object> map) {
		if(map!=null){
			for(String k :map.keySet()){
				query.setParameter(k, map.get(k));
			}
		}
	}
	/**
	 * 传入map设置参数
	 * @param query
	 * @param array
	 * add by penkee@163.com
	 */
	public void setParam(Query query,Object[] array) {
		int i=0;
		if(array!=null){
			for(Object k :array){
				query.setParameter(++i, k);
			}
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
