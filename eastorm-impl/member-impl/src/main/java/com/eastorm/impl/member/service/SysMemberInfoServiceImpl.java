package com.eastorm.impl.member.service;

import com.eastorm.api.member.domain.SysMemberInfo;
import com.eastorm.api.member.service.SysMemberInfoService;
import com.eastorm.core.base.sys.EastormException;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.impl.member.dao.SysMemberInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMemberInfoServiceImpl   implements SysMemberInfoService {
	@Autowired
	private SysMemberInfoDao memberInfoDao;

	@Override
	public SysMemberInfo findBySysMemberId(Integer sysMemberId) {
		long c=memberInfoDao.countBySysMemberId(sysMemberId);
		// TODO Auto-generated method stub
		List<SysMemberInfo> list= memberInfoDao.findBySysMemberId(sysMemberId);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateUserInfo(Integer sysMemberId, SysMemberInfo memberInfo) throws EastormException {
		// TODO Auto-generated method stub
		if(memberInfo!=null){
			SysMemberInfo dbInfo=findBySysMemberId(sysMemberId);
			if(dbInfo==null)throw new EastormException(Const.FR_FAILTURE,"用户不存在");
			//更新性别
			if(memberInfo.getSex()!=null)dbInfo.setSex(memberInfo.getSex());
			if(memberInfo.getBirthday()!=null)dbInfo.setBirthday(memberInfo.getBirthday());
			//昵称
			if(!StringFunc.isNull(memberInfo.getNickname()))dbInfo.setNickname(StringFunc.URLDecoder(memberInfo.getNickname()));
			memberInfoDao.save(dbInfo);
		}
	}

	/* (non-Javadoc)
	 * @see SysMemberInfoService#updateJF(java.lang.Integer, int)
	 */
	@Override
	public void updateJF(Integer sysMemberId, int score) throws EastormException {
		// TODO Auto-generated method stub
		memberInfoDao.updateScore(sysMemberId,score);
	}

	@Override
	public SysMemberInfo findOne(Integer id) {
		return memberInfoDao.findOne(id);
	}

	@Override
	public SysMemberInfo queryBySysMemberId(Integer userid) {
		// TODO Auto-generated method stub
		List<SysMemberInfo> list=memberInfoDao.queryBySysMemberId(userid);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(SysMemberInfo memberInfo) {
		// TODO Auto-generated method stub
		memberInfoDao.save(memberInfo);
	}
}
