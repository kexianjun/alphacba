package ke.alphacba.cms.core.api.dto.cms;

import ke.alphacba.cms.core.api.dto.base.BaseResp;
import ke.alphacba.cms.core.api.pojo.UserInfo;

@SuppressWarnings("serial")
public class UserInfoResp extends BaseResp {
	private UserInfo respItem;

	public UserInfo getRespItem() {
		return respItem;
	}

	public void setRespItem(UserInfo respItem) {
		this.respItem = respItem;
	}
	
	
}
