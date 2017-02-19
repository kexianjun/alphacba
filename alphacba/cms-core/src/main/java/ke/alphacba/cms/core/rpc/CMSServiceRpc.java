package ke.alphacba.cms.core.rpc;

import ke.alphacba.cms.core.api.dto.cms.UserInfoReq;
import ke.alphacba.cms.core.api.dto.cms.UserInfoResp;

public interface CMSServiceRpc {
	String helloWorld(String name);
	UserInfoResp getUserInfo(UserInfoReq userInfoReq);
}
