package ke.alphacba.cms.core.rpc;

import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;

public interface CMSServiceRpc {
	String helloWorld(String name);
	UserInfoResp getUserInfo(UserInfoReq userInfoReq);
}
