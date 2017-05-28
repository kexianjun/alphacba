package ke.alphacba.cms.service.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cms.base.api.pojo.UserInfo;

import ke.alphacba.cms.core.cache.loader.AbstractCacheLoader;
import ke.alphacba.cms.core.constant.CacheNameConstants;

@Component
public class UserInfoCacheLoader extends AbstractCacheLoader<UserInfo> {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoCacheLoader.class);
	@Override
	public Map<String, UserInfo> loadData() {
		logger.debug("load user info to cache");
		Map<String, UserInfo> userInfos = getAllUserInfo();
		return userInfos;
	}
	
	private Map<String, UserInfo> getAllUserInfo(){
		Map<String, UserInfo> userInfos = new HashMap<>();
		for(int i = 0; i < 10; i++){
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId("1001");
			userInfo.setUserName("UserName" + i);
			userInfos.put(CacheNameConstants.USER_INFO_PREFIX + userInfo.getUserId(), userInfo);
		}
		return userInfos;
		
	}
	
}
