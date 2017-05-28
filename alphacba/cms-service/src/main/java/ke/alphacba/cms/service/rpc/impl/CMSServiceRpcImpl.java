package ke.alphacba.cms.service.rpc.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;

import ke.alphacba.cms.core.rpc.CMSServiceRpc;
import ke.alphacba.cms.service.dao.UserInfoDao;

public class CMSServiceRpcImpl implements CMSServiceRpc {

	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public String helloWorld(String name) {
		return "Hello" + name;
	}

	@Override
	public UserInfoResp getUserInfo(UserInfoReq userInfoReq) {
		return userInfoDao.getUserInfo(userInfoReq);
	}
}
