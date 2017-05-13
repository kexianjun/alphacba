package ke.alphacba.cms.core.cache;

public interface SysCacheService {
	boolean removeObject(String key);
	<T> boolean saveObject(String key, T object);
	<T>T getObject(String key, Class<T> clazz);
}
