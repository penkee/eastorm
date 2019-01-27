package com.eastorm.api.member.service;

import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.core.base.sys.EastormException;

import java.util.List;

public interface SysLoginService {
        /**
         * 根据用户名查找一条记录
         * @param username
         * @return
         * add by penkee@163.com
         */
		SysLogin findOneByUsername(String username);
		/**
		 * 根据用户名查找一条记录,级联
		 * (这里描述这个方法适用条件 – 可选)
		 * @param username
		 * @return
		 * @return SysLogin
		 */
		public SysLogin queryOneByUsername(String username);
		SysLogin findOne(Integer id);
		/**
		 * @param login
		 * add by penkee@163.com
		 */
		void save(SysLogin login);
        /**
         * 根据用户名记录数
         * @param username
         * @return
         * add by penkee@163.com
         */
		int queryCountByUsername(String username);
		/**
		 * 注册
		 * added by Buke at 2016年4月17日
		 */
		SysMember insertFrontUser(String username, String password, String openid, String alias, String headurl, String verifyCode, Byte sex) throws EastormException;
		List<SysLogin> findByMemberId(Integer id);
		/**
		 * 重置密码
		 * @param username
		 * @param password 
		 * added by Buke at 2016年5月29日
		 */
		public void resetPwd(String username, String password) throws EastormException;
}
