package ke.alphacba.cms.core.cache;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

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
	public boolean saveObject(String key, Object object) {
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
	public Object getObject(String key, Class<?> clazz) {
		Object object = super.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bytes = connection.get(key.getBytes());
				Object redisObject = objectDeserialize(bytes, clazz);
				return redisObject;
			}
		});
		return object;
	}
	
	private byte[] objectSerialize(Object object){
		byte [] bytes = null;
		try {
			bytes = objectMapper.writeValueAsString(object).getBytes();
		} catch (Exception e) {
			logger.error("error:", e);
		}
		return bytes;
	}
	
	private Object objectDeserialize(byte[] bytes, Class<?> clazz){
		Object object = null;
		try {
			object = objectMapper.readValue(bytes, clazz);
		} catch (Exception e) {
			logger.error("error:", e);
		}
		return object;
	}
}
