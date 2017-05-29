package ke.alphacba.cms.service.as;

import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;

public interface UserInfoAtomService {
	UserInfoResp getUserInfo( UserInfoReq req);
}
