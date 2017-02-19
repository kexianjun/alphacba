package ke.alphacba.cms.service.dao.impl;

import org.springframework.stereotype.Repository;

import ke.alphacba.cms.core.api.dto.base.BaseResp;
import ke.alphacba.cms.core.api.dto.cms.UserInfoReq;
import ke.alphacba.cms.core.api.dto.cms.UserInfoResp;
import ke.alphacba.cms.core.api.pojo.UserInfo;
import ke.alphacba.cms.core.base.dao.BaseDao;
import ke.alphacba.cms.service.dao.UserInfoDao;

@Repository
public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {
	private static final String SQPMAP_SPACE = "ke.alphacba.cms.UserInfo.";

	@Override
	public UserInfoResp getUserInfo(UserInfoReq userInfoReq) {
		UserInfoResp userInfoResp = new UserInfoResp();
		UserInfo userInfo = (UserInfo)getSqlSession().selectOne(SQPMAP_SPACE + "selectUserInfo", userInfoReq.getParams());
		userInfoResp.setRespItem(userInfo);
		return userInfoResp;
	}

	@Override
	public BaseResp addUserInfo(UserInfoReq userInfoReq) {
		// TODO Auto-generated method stub
		return null;
	}

}
