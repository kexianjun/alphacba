package ke.alphacba.cms.service.rpcimpl;

import org.springframework.beans.factory.annotation.Autowired;

import ke.alphacba.cms.core.api.dto.cms.UserInfoReq;
import ke.alphacba.cms.core.api.dto.cms.UserInfoResp;
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
