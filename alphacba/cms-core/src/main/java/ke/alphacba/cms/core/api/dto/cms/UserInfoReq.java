package ke.alphacba.cms.core.api.dto.cms;

import ke.alphacba.cms.core.api.dto.base.BaseReq;
import ke.alphacba.cms.core.api.pojo.UserInfo;

@SuppressWarnings("serial")
public class UserInfoReq extends BaseReq {
	private UserInfo params;

	public UserInfo getParams() {
		return params;
	}

	public void setParams(UserInfo params) {
		this.params = params;
	}
	
	
}
