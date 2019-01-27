package com.eastorm.api.member.service;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.api.member.domain.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @brief     会员
 * @details   详细描述
 * @param
 * @retval    返回值
 * @author    慕容恪
 * @date      2018/8/12
 * @note      变更信息
 * @return
*/
public interface SysMemberService {
		/**
		 * 查询role
		 * @return
		 * add by penkee@163.com
		 */
		List<SysRole> queryRole(Integer id);
		
		/**
		 * 查询列表
		 * @return
		 * add by penkee@163.com
		 */
		Page<Map<String,Object>> queryPage(Pageable page,Map<String,String[]> searchMap);

		/**新增sysLogin和sysMember表
		 * @param login
		 * add by penkee@163.com
		 */
		void insertLoginAndMember(SysLogin login);
		/**
		 * 
		 * @param id
		 * @return
		 * add by penkee@163.com
		 */
		SysMember findOne(Integer id);
		/**
		 * 查询列表-tKey代表sysMemberId,name-login.username
		 * @return
		 * add by penkee@163.com
		 */
		List<CommType> queryListByUsername(String username);
		
		String encodeUserid(Integer id);
		
		Integer decodeUserid(String id);

		Page<Map<String, Object>> queryBackExport(PageRequest pageRequest,
				Map<String, String[]> parameterMap);
}
