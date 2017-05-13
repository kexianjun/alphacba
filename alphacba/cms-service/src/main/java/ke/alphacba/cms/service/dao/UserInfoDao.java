package ke.alphacba.cms.service.dao;

import ke.alphacba.cms.core.api.dto.base.BaseResp;
import ke.alphacba.cms.core.api.dto.cms.UserInfoReq;
import ke.alphacba.cms.core.api.dto.cms.UserInfoResp;

public interface UserInfoDao {
	UserInfoResp getUserInfo(UserInfoReq userInfoReq);
	BaseResp addUserInfo(UserInfoReq userInfoReq);
	UserInfoResp pageQueryUserInfo(UserInfoReq userInfoReq);
	
}	
