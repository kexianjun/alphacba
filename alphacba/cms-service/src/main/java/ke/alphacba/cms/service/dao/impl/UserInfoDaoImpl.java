package ke.alphacba.cms.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.cms.base.api.base.dto.BaseResp;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.pojo.UserInfo;

import ke.alphacba.cms.core.base.dao.BaseDao;
import ke.alphacba.cms.service.dao.UserInfoDao;

@Repository
public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {
	private static final String SQPMAP_SPACE = "ke.alphacba.cms.UserInfo.";

	@Override
	public UserInfoResp getUserInfo(UserInfoReq userInfoReq) {
		UserInfoResp userInfoResp = new UserInfoResp();
		UserInfo userInfo = (UserInfo) getSqlSession().selectOne(SQPMAP_SPACE + "selectUserInfo",
				userInfoReq.getParams());
		userInfoResp.setRespItem(userInfo);
		return userInfoResp;
	}

	@Override
	public BaseResp addUserInfo(UserInfoReq userInfoReq) {
		return null;
	}

	@Override
	public UserInfoResp pageQueryUserInfo(UserInfoReq userInfoReq) {
		return null;
	}

}
