package ke.alphacba.cms.core.meta;

import ke.alphacba.cms.core.cache.redis.SysCacheService;
import ke.alphacba.cms.core.context.SpringContext;

public class MetaDataService {
	private static SysCacheService SYS_CACHE_SERVICE;
	{
		SYS_CACHE_SERVICE = SpringContext.getBean("sysCacheService", SysCacheService.class);
	}
	
	public static String getErrorInfo(int errorNo){
		return null;
	}
}
