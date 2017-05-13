package ke.alphacba.cms.core.api.dto.cms;

import java.util.List;

import ke.alphacba.cms.core.api.dto.base.BaseResp;
import ke.alphacba.cms.core.api.pojo.UserInfo;

@SuppressWarnings("serial")
public class UserInfoResp extends BaseResp {
	private UserInfo respItem;
	private List<UserInfo> respItems;
	

	public List<UserInfo> getRespItems() {
		return respItems;
	}

	public void setRespItems(List<UserInfo> respItems) {
		this.respItems = respItems;
	}

	public UserInfo getRespItem() {
		return respItem;
	}

	public void setRespItem(UserInfo respItem) {
		this.respItem = respItem;
	}
	
	
}
