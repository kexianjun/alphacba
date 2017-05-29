package ke.alphacba.cms.service.rpc.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cms.base.api.constants.ErrorNoConstants;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.rpc.CMSServiceRpc;

import ke.alphacba.cms.service.ls.UserInfoService;

public class CMSServiceRpcImpl implements CMSServiceRpc {

	@Autowired
	private UserInfoService userInfoService;
	@Override
	public String helloWorld(String name) {
		return "Hello" + name;
	}

	@Override
	public UserInfoResp getUserInfo(UserInfoReq userInfoReq) {
		UserInfoResp userInfoResp = null;
		if (null == userInfoReq || null == userInfoReq.getParams()) {
			userInfoResp = new UserInfoResp();
			userInfoResp.setErrorNo(ErrorNoConstants.PARAMS_EMPTY_ERROR);
			return userInfoResp;
		}
		return userInfoService.getUserInfoByUserId(userInfoReq.getParams().getUserId());
	}
}
