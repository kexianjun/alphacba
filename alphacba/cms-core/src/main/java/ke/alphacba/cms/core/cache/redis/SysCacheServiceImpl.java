package ke.alphacba.cms.core.cache.redis;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import ke.alphacba.cms.core.cache.SysCacheService;

public class SysCacheServiceImpl extends RedisTemplate<String, Object> implements SysCacheService {
	private ObjectMapper objectMapper;
	public SysCacheServiceImpl(JedisConnectionFactory factory) throws IOException {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
		super.setConnectionFactory(factory);
	}

	@Override
	public boolean removeObject(String key) {
		boolean result = super.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.del(key.getBytes());
				return true;
			}
		});
		return result;
	}

	@Override
	public <T> boolean saveObject(String key, T object) {
		
		boolean result = super.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(key.getBytes(), objectSerialize(object));
				return true;
			}
		});
		return result;
	}
	
	@Override
	public <T> T getObject(String key, Class<T> clazz) {
		T result = super.execute(new RedisCallback<T>() {

			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bytes = connection.get(key.getBytes());
				T retObj = objectDeserialize(bytes, clazz);
				return retObj;
			}
		});
		return result;
	}
	
	private <T>byte[] objectSerialize(T object){
		byte [] bytes = null;
		try {
			bytes = objectMapper.writeValueAsString(object).getBytes();
		} catch (Exception e) {
			logger.error("error:", e);
		}
		return bytes;
	}
	
	private <T> T objectDeserialize(byte[] bytes, Class<T> clazz){
		T object = null;
		try {
			object = objectMapper.readValue(bytes, clazz);
		} catch (Exception e) {
			logger.error("error:", e);
		}
		return object;
	}

}
