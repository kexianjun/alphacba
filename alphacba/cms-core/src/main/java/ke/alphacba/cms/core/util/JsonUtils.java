package ke.alphacba.cms.core.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.codehaus.jackson.map.ser.std.NullSerializer;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class JsonUtils {


	    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

	    final static ObjectMapper objectMapper;

	    /**
	     * 是否打印美观格式
	     */
	    static boolean isPretty = false;

	    static {
	        StdSerializerProvider sp = new StdSerializerProvider();
	        sp.setNullValueSerializer(NullSerializer.instance);
	        objectMapper = new ObjectMapper(null, sp, null);
	        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
	        objectMapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	    }

	    public static ObjectMapper getObjectMapper() {
	        return objectMapper;
	    }
	    /**
	     * JSON串转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
	     * @param <T>
	     * @param jsonString JSON字符串
	    * @param tr TypeReference,例如: new TypeReference< List<FamousUser> >(){}
	     * @return List对象列表
	     */
	    @SuppressWarnings("unchecked")
		public static <T> T json2GenericObject(String jsonString, TypeReference<T> tr) {

	        if (jsonString == null || "".equals(jsonString)) {
	            return null;
	        } else {
	            try {
	                return (T) objectMapper.readValue(jsonString, tr);
	            } catch (Exception e) {
	                log.warn("json error:" + e.getMessage());
	            }
	        }
	        return null;
	    }

	    /**
	     * Java对象转Json字符串
	     *
	     * @param object Java对象，可以是对象，数组，List,Map等
	     * @return json 字符串
	     */
	    public static String toJson(Object object) {
	        String jsonString = "";
	        try {
	            if (isPretty) {
	                jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	            } else {
	                jsonString = objectMapper.writeValueAsString(object);
	            }
	        } catch (Exception e) {
	            log.warn("json error:" + e.getMessage());
	        }
	        return jsonString;
	    }

	    /**
	     * Json字符串转Java对象
	     *
	     * @param jsonString
	     * @param c
	     * @return
	     */
	    public static Object json2Object(String jsonString, Class<?> c) {
	        if (jsonString == null || "".equals(jsonString)) {
	            return "";
	        } else {
	            try {
	                return objectMapper.readValue(jsonString, c);
	            } catch (Exception e) {
	                log.warn("json error:" + e.getMessage());
	            }
	        }
	        return "";
	    }

	    public static <T> List<T> json2List(String jsonString, Class<T> c){
	    	List<T> list = null;
	        try {
	            JSONArray array = new JSONArray(jsonString);
	            if(array.length()>0){
	            	list = new ArrayList<T>();
	            	for(int i = 0; i < array.length(); i++) {
	            		T item = objectMapper.readValue(array.getString(i), c);
	            		list.add(item);
	            	}
	            }
	        } catch (JsonParseException e) {
	        	log.warn("json error:" + e.getMessage());
	        } catch (JsonMappingException e) {
	        	log.warn("json error:" + e.getMessage());
	        } catch (IOException e) {
	        	log.warn("json error:" + e.getMessage());
	        } catch (JSONException e) {
	        	log.warn("json error:" + e.getMessage());
			}
	    	return list;
	    }
	    
	    public static <T> List<T> json2ListAppointed (String content, Class<T> clazz){
	    	JavaType valueType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
	    	try {
				return objectMapper.readValue(content, valueType);
			} catch (JsonParseException e) {
				log.error("json error:" + e.getMessage());
			} catch (JsonMappingException e) {
				log.error("json error:" + e.getMessage());
			} catch (IOException e) {
				log.error("json error:" + e.getMessage());
			}
	    	return null;
	    }

	    public static String getNodeJson(String content, String nodeName){
			try {
				JsonNode nodes = objectMapper.readTree(content);
				String itemsJson = nodes.get(nodeName).toString(); 
				return itemsJson;
			} catch (JsonProcessingException e) {
				log.error("json error:" + e.getMessage());
			} catch (IOException e) {
				log.error("json error:" + e.getMessage());
			}
			return null;
	    }
	    
	    private static final JsonConverter converter = new JackJsonConverter();

		/**
		 * 序列化,即对象转成json字符串.
		 * 
		 * @param value
		 * @return
		 * 
		 */
		public static String serialize(Object value) {
			return converter.serialize(value);
		}

		/**
		 * 反序列化,即json字符串转成对象
		 * 
		 * @param json
		 * @param valueType
		 *            对象类型
		 * @return
		 * 
		 */
		public static <T> T deserialize(String json, Class<T> valueType) {
			return converter.deserialize(json, valueType);
		}

		/** 功能说明: Json数据转换器接口 */
		public interface JsonConverter {

			/**
			 * 序列化,即对象转成json字符串.
			 * 
			 * @param value
			 * @return
			 * 
			 */
			String serialize(Object value);

			/**
			 * 反序列化,即json字符串转成对象
			 * 
			 * @param json
			 * @param valueType
			 *            对象类型
			 * @return
			 * 
			 */
			<T> T deserialize(String json, Class<T> valueType);
		}

		/**
		 * 功能说明: 使用字段名作为json字符串中键值策略.<br>
		 * 注意事项: jackjson默认策略是直接从get\set方法去掉前缀后,取接下来的大写字母(如果存在连续大写,则一并转换)转换成小写.<br>
		 * 例如,实际字段名为aBc,按照标准JavaBean规范,对应存在getABc\setABc方法,这时下面的defaultName是abc.<br>
		 * 系统版本: v1.0<br>
		 * 开发人员: wangkx07777@hundsun.com<br>
		 * 开发时间: 2014-4-4<br>
		 */
		static class UseFieldNameStrategy extends PropertyNamingStrategy {

			@Override
			public String nameForGetterMethod(MapperConfig<?> config,
					AnnotatedMethod method, String defaultName) {
				return nameForMethod(method, defaultName, "get");
			}

			@Override
			public String nameForSetterMethod(MapperConfig<?> config,
					AnnotatedMethod method, String defaultName) {
				return nameForMethod(method, defaultName, "set");
			}

			private String nameForMethod(AnnotatedMethod method,
					String defaultName, String methodNamePrefix) {
				String fieldName = method.getName().replaceFirst(methodNamePrefix,
						"");
				if (fieldName.length() >= 2) {
					StringBuilder result = new StringBuilder(fieldName);
					// 如果第1个是大写并且第2个也是大写,则第一个转为小写
					if (isUpperCase(result.charAt(0))
							&& isUpperCase(result.charAt(1))) {
						result.setCharAt(0, Character.toLowerCase(result.charAt(0)));
						defaultName = result.toString();
					}
				}
				return defaultName;
			}

			private boolean isUpperCase(char c) {
				return Character.isUpperCase(c);
			}

		}

		/** JackJson转换器 */
		static class JackJsonConverter implements JsonConverter {

			private static final ObjectMapper mapper = new ObjectMapper();// 全局变量,线程安全,默认不过滤null属性.

			static {
				mapper.configure(
						DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);// 忽略未知属性
				mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
						true);// 允许字段名没有引号
				mapper.setSerializationInclusion(Inclusion.NON_NULL);// 序列化时不输出null值的键值对.
				mapper.setPropertyNamingStrategy(new UseFieldNameStrategy());// 定制字段名策略
			}

			@Override
			public String serialize(Object value) {
				try {
					return mapper.writeValueAsString(value);
				} catch (Exception e) {
					log.error("要序列化的对象：" + value.toString());
					throw new RuntimeException("序列化失败");
				}
			}

			@Override
			public <T> T deserialize(String json, Class<T> valueType) {
				try {
					return mapper.readValue(json, valueType);
				} catch (Exception e) {
					log.error("要反序列化的字符串：" + json);
					throw new RuntimeException("序列化失败");
				}
			}

		}

		/** FastJson转换器 */
		static class FastJsonConverter implements JsonConverter {

			@Override
			public String serialize(Object value) {
				try {
					return JSON.toJSONString(value);
				} catch (Exception e) {
					log.error("要序列化的对象：" + value.toString());
					throw new RuntimeException("序列化失败");
				}
			}

			@Override
			public <T> T deserialize(String json, Class<T> valueType) {
				try {
					return JSON.parseObject(json, valueType);
				} catch (Exception e) {
					log.error("要反序列化的字符串：" + json);
					throw new RuntimeException("序列化失败");
				}
			}   
		}

}
