package ke.alphacba.cms.service.as.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.base.api.constants.ErrorNoConstants;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.pojo.UserInfo;

import ke.alphacba.cms.service.as.UserInfoAtomService;
import ke.alphacba.cms.service.dao.UserInfoDao;

@Component
public class UserInfoAtomServiceImpl implements UserInfoAtomService {
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public UserInfoResp getUserInfo(UserInfoReq req) {
		UserInfoResp resp = new UserInfoResp();
		if (null == req || null == req.getParams()) {
			resp.setErrorNo(ErrorNoConstants.PARAMS_EMPTY_ERROR);
			return resp;
		}
		UserInfo userInfo = req.getParams();
		UserInfo result = userInfoDao.getUserInfo(userInfo);
		resp.setRespItem(result);
		return resp;
	}
	
	
}
