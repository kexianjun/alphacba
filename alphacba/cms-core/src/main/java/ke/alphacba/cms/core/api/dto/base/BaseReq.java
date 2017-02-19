package ke.alphacba.cms.core.api.dto.base;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseReq implements Serializable {
	private long loginUserId;
	private String loginUserName;
	
	public long getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(long loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	
}
