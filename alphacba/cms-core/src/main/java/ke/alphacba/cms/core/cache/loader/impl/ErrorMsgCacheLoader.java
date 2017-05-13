package ke.alphacba.cms.core.cache.loader.impl;

import java.util.HashMap;
import java.util.Map;

import ke.alphacba.cms.core.cache.loader.AbstractCacheLoader;

public class ErrorMsgCacheLoader extends AbstractCacheLoader<String> {

	@Override
	public Map<String, String> loadData() {
		return getAllErrorMsg();
	}
	
	private Map<String, String>getAllErrorMsg(){
		Map<String, String> errorMsgs = new HashMap<>();
		
		return errorMsgs;
	}

}
