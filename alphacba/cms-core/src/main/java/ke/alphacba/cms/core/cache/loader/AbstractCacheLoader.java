package ke.alphacba.cms.core.cache.loader;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import ke.alphacba.cms.core.cache.SysCacheService;

public abstract  class AbstractCacheLoader <T> implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private SysCacheService SysCacheService;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
	
		new Thread(new Runnable() {
			@Override
			public void run() {
				loadDataToCache();
			}
		}).start();
	}
	
	public void loadDataToCache(){
		Map<String, T> dataMap = loadData();
		if (CollectionUtils.isEmpty(dataMap)) {//加载到的数据为空则直接返回
			return;
		}
		Set<Entry<String, T> > entrySet = dataMap.entrySet();
		Iterator<Entry<String, T>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, T> entry = iterator.next();
			SysCacheService.saveObject(entry.getKey(), entry.getValue());
		}
	}
	
	public abstract Map<String, T> loadData();
}
