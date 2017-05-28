package ke.alphacba.cms.service.as.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ke.alphacba.cms.service.as.UserInfoAtomService;
import ke.alphacba.cms.service.dao.UserInfoDao;

@Component
public class UserInfoAtomServiceImpl implements UserInfoAtomService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	
}
