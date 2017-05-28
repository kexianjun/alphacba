package ke.alphacba.cms.service.dao;

import com.cms.base.api.base.dto.BaseResp;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;

public interface UserInfoDao {
	UserInfoResp getUserInfo(UserInfoReq userInfoReq);
	BaseResp addUserInfo(UserInfoReq userInfoReq);
	UserInfoResp pageQueryUserInfo(UserInfoReq userInfoReq);
	
}	
