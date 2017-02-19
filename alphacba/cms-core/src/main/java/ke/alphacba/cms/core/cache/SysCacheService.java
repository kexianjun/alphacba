package ke.alphacba.cms.core.cache;

public interface SysCacheService {
	boolean removeObject(String key);
	boolean saveObject(String key, Object object);
	Object getObject(String key, Class<?> clazz);
}
