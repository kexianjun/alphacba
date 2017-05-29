package ke.alphacba.cms.core.meta;

import java.text.MessageFormat;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cms.base.api.base.pojo.UserAgent;
import com.cms.base.api.pojo.UserInfo;

import ke.alphacba.cms.core.cache.SysCacheService;
import ke.alphacba.cms.core.constant.CacheNameConstants;
import ke.alphacba.cms.core.constant.ErrorNoConstants;
import ke.alphacba.cms.core.context.SpringContext;
@Component
@Lazy
public class MetaDataService {
	private static SysCacheService SYS_CACHE_SERVICE;
	
	public static String getErrorInfo(int errorNo, Object[] errorParams){
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
		if (ErrorNoConstants.OK_SUCCESS == errorNo) {
			return "操作成功";
		}
		String errorMsgPattern =  
				SYS_CACHE_SERVICE.getObject(CacheNameConstants.ERROR_MSG_PREFIX, String.class);
		String errorMsg = MessageFormat.format(errorMsgPattern, errorParams);
		return errorMsg;
	}
	
	public static boolean saveUserInfoToCache(UserInfo userInfo){
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
		String key = CacheNameConstants.USER_INFO_PREFIX + userInfo.getUserId();
		return SYS_CACHE_SERVICE.saveObject(key, userInfo);
	}
	
	public static UserInfo getUserInfoByUserId(String userId){
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
		String key = CacheNameConstants.USER_INFO_PREFIX + userId;
		UserInfo userInfo = SYS_CACHE_SERVICE.getObject(key, UserInfo.class);
		return userInfo;
	}
	
	public static boolean removeUserInfo(String userId){
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
		String key = CacheNameConstants.USER_INFO_PREFIX + userId;
		return SYS_CACHE_SERVICE.removeObject(key);
	}
	
	public static boolean savaSessionObj(String sessionId, UserAgent userAgent){
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
		String key = sessionId + CacheNameConstants.SESSION_AGENT_SEPERATOR + userAgent.getUserId();
		return SYS_CACHE_SERVICE.saveObject(key, userAgent);
	}
	
	public static UserAgent getUserAgentBySessionId(String sessionId,String userId){
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
		String key = sessionId + CacheNameConstants.SESSION_AGENT_SEPERATOR + userId;
		return SYS_CACHE_SERVICE.getObject(key, UserAgent.class);
	}
	
	public static boolean removeSessionObj(String sessionId, String userId){
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
		String key = sessionId + CacheNameConstants.SESSION_AGENT_SEPERATOR + userId;
		return SYS_CACHE_SERVICE.removeObject(key);
	}
}
