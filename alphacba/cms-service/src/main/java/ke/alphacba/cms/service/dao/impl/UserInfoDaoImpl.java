package ke.alphacba.cms.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cms.base.api.base.dto.BaseResp;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.pojo.UserInfo;

import ke.alphacba.cms.core.base.dao.BaseDao;
import ke.alphacba.cms.service.dao.UserInfoDao;

@Repository
public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {
	private static final String SQPMAP_SPACE = "ke.alphacba.cms.UserInfo.";

	@Override
	public UserInfo getUserInfo(UserInfo userInfo) {
		UserInfo result = (UserInfo) getSqlSession().selectOne(SQPMAP_SPACE + "selectUserInfo",
				userInfo);
		return result;
	}

	@Override
	public boolean addUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserInfo> pageQueryUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	

	}
