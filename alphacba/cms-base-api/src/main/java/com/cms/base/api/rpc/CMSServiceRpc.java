package com.cms.base.api.rpc;

import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;

public interface CMSServiceRpc {
	String helloWorld(String name);
	UserInfoResp getUserInfo(UserInfoReq userInfoReq);
	/*用户登陆*/
	UserInfoResp userLogin(UserInfoReq userInfoReq);
}
