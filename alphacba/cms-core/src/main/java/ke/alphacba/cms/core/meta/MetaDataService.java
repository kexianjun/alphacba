package ke.alphacba.cms.core.meta;

import java.text.MessageFormat;

import ke.alphacba.cms.core.cache.SysCacheService;
import ke.alphacba.cms.core.constant.CacheNameConstants;
import ke.alphacba.cms.core.constant.ErrorNoConstants;
import ke.alphacba.cms.core.context.SpringContext;

public class MetaDataService {
	private static SysCacheService SYS_CACHE_SERVICE;
	{
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
	}
	
	public static String getErrorInfo(int errorNo, Object[] errorParams){
		if (ErrorNoConstants.OK_SUCCESS == errorNo) {
			return "操作成功";
		}
		String errorMsgPattern =  
				SYS_CACHE_SERVICE.getObject(CacheNameConstants.ERROR_MSG_PREFIX, String.class);
		String errorMsg = MessageFormat.format(errorMsgPattern, errorParams);
		return errorMsg;
	}
}
