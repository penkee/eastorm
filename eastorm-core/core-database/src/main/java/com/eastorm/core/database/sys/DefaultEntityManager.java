/**
 *DefaultEntityManager.java
	Description:
 */
package com.eastorm.core.database.sys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-11-3
 */
public interface DefaultEntityManager {
	/**
	 * @brief     查询原生sql（分页）参数随意
	 * @details   详细描述
	 * @param
	 * @retval    返回值
	 * @author    慕容恪
	 * @date      2018/8/12
	 * @note      变更信息
	 * @return
	 */
	Page<Object[]> queryPageNative(Pageable page, String sql, Object... param);
	/**
	 * @brief     查询原生sql（分页）参数map
	 * @details   详细描述
	 * @param
	 * @retval    返回值
	 * @author    慕容恪
	 * @date      2018/8/12
	 * @note      变更信息
	 * @return
	*/
	Page queryPageNativeByMap(Pageable page, String sql,Map<String, Object> map);
	/**
	 * 查询（分页）参数map
	 * @param page
	 * @param jpql
	 * @param map
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	Page queryPageByMap(Pageable page,String jpql,Map<String,Object> map);
	/**
	 * 查询（分页）参数随意
	 * @param page
	 * @param jpql
	 * @param param
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	Page queryPage(Pageable page,String jpql,Object ...param);
	/**
	 * 查询（分页）参数map,传入原生SQL
	 * @param page
	 * @param countSql  统计的sql
	 * @param sql
	 * @param map
	 * @return 
	 * added by Buke at 2016年5月15日
	 */
	Page<Map<String, Object>> queryPageMapNativeByMap(Pageable page, String countSql,
			String sql,Map<String, Object> map);
	/**
	 * 查询（分页）参数map,传入原生SQL
	 * count默认查出来
	 * @param page
	 * @param sql
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	Page<Map<String, Object>> queryPageMapNativeByMap(Pageable page,String sql,Map<String,Object> map);

	/**
	 * 查询参数map,传入原生SQL
	 * @param sql
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String, Object>> queryListNativeByMap(String sql,Map<String,Object> map);
	/**
	 * 查询参数随意,传入原生SQL
	 * @param sql
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String, Object>> queryListNative(String sql,Object ...param);
	/**
	 * 命名查询map
	 * @param name
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	List queryNamedByMap(String name,Map<String,Object> map);
	/**
	 * 命名查询,参数随意
	 * @param name
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	List queryNamed(String name,Object ...param);
	/**
	 * 命名查询,参数随意
	 * @param name
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String, Object>> queryMapNamed(String name,Object ...param);
	
	/**
	 * 直接传入jpql,,参数map
	 * @param jpql
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	List query(String jpql,Map<String,Object> map);
	
	/**
	 * 直接传入jpql,参数随意
	 * @param jpql
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	List query(String jpql,Object ...param);
	
	/**
	 * 命名查询一条记录,map
	 * @param name
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	Object queryNamedOneByMap(String name,Map<String,Object> map);
	/**
	 * 命名查询一条记录,参数随意
	 * @param name
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	Object queryNamedOne(String name,Object ...param);
	
	/**
	 * 直接传入jpql查询一条记录,参数map
	 * @param jpql
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	Object queryOne(String jpql,Map<String,Object> map);
	
	/**
	 * 直接传入jpql查询一条记录,参数随意
	 * @param sql
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	Object queryOne(String sql,Object ...param);


	/*****************************UPDATE OPERATION*****************************************************************/
	/**
	 * 更新和删除操作
	 * @param jpql
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	int updateByMap(String jpql,Map<String,Object> map);
	/**
	 *  更新和删除操作
	 * @param jpql
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	int update(String jpql,Object ...param);
	/**
	 *  更新和删除操作
	 * @param name
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	int updateNamedByMap(String name,Map<String,Object> map);
	/**
	 *  更新和删除操作
	 * @param name
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	int updateNamed(String name,Object ...param);
	/**
	 *  更新和删除操作native
	 * @param sql
	 * @param param
	 * @return
	 * add by penkee@163.com
	 */
	int updateNative(String sql, Object... param);
}
