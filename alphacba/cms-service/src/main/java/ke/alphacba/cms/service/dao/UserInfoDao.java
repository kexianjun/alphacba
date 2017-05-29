package ke.alphacba.cms.service.dao;

import java.util.List;

import com.cms.base.api.base.dto.BaseResp;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.pojo.UserInfo;

public interface UserInfoDao {
	UserInfo getUserInfo(UserInfo userInfo);
	boolean addUserInfo(UserInfo userInfo);
	List<UserInfo> pageQueryUserInfo(UserInfo userInfo);
	
}	
