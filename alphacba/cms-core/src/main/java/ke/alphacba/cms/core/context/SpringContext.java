package ke.alphacba.cms.core.context;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.WebUtils;


public class SpringContext extends ApplicationObjectSupport implements InitializingBean, ServletContextAware{



	private static ApplicationContext webApplicationContext;
	private static ServletContext servletContext;
	private static String appDomain;

	@Value("${app.domain}")
	public void setAppDomain(String appDomain) {
		SpringContext.appDomain = appDomain;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		SpringContext.servletContext = servletContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SpringContext.webApplicationContext = super.getApplicationContext(); // 默认取到的root容器还是dispatchServlet容器取决于SpringContext在什么地方加载，Listener中为root，servlet中为后者。
		// SpringContext.rootApplicationContext = (ApplicationContext)
		// servletContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		System.out.println("SpringContext.webApplicationContext【" + webApplicationContext.getDisplayName() + "】初始化完成");
		// System.out.println("SpringContext.rootApplicationContext【" +
		// rootApplicationContext.getDisplayName() + "】初始化完成");
		// Assert.notNull(webApplicationContext, "webApplicationContext不能为空!");
		// Assert.notNull(rootApplicationContext,
		// "rootApplicationContext不能为空!");
		Assert.notNull(servletContext, "servletContext不能为空!");
		Assert.notNull(appDomain, "appDomain不能为空!");
	}

	/**
	 * 根据名称获取Bean
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		try {
			return webApplicationContext.getBean(beanName);
		} catch (Exception e) {
			throw new RuntimeException("获取bean[" + beanName + "]失败", e);
		}
	}

	/**
	 * 根据名称，类型获取Bean
	 * 
	 * @param beanName
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> requiredType) {
		try {
			return webApplicationContext.getBean(beanName, requiredType);
		} catch (Exception e) {
			throw new RuntimeException("获取bean[" + beanName + "]失败,指定type[" + requiredType + "]", e);
		}
	}

	/**
	 * 根据类型获取Bean Map
	 * 
	 * @param type
	 * @return
	 */
	public static <T> Map<String, T> getBeanMapOfType(Class<T> type) {
		try {
			return webApplicationContext.getBeansOfType(type);
		} catch (Exception e) {
			throw new RuntimeException("获取bean失败,指定type[" + type + "]", e);
		}
	}

	/**
	 * 根据类型获取Bean List
	 * 
	 * @param type
	 * @return
	 */
	public static <T> List<T> getBeanListOfType(Class<T> type) {
		Map<String, T> beanMap = getBeanMapOfType(type);
		List<T> list = new ArrayList<T>();
		if (!beanMap.isEmpty()) {
			list.addAll(beanMap.values());
		}
		return list;
	}

	/**
	 * 根据类型获取Bean Array
	 * 
	 * @param type
	 * @return
	 */
	public static <T> T[] getBeanArrayOfType(Class<T> type) {
		List<T> list = getBeanListOfType(type);
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(type, list.size());
		array = list.toArray(array);
		return array;
	}

	/**
	 * 获取当前应用在Servlet容器中的临时文件目录
	 * 
	 * @return
	 */
	public static File getTempDir() {
		try {
			return WebUtils.getTempDir(servletContext);
		} catch (Exception e) {
			throw new RuntimeException("获取临时目录失败！", e);
		}
	}

	/**
	 * 获取应用域名
	 * 
	 * @return
	 */
	public static String getAppDomain() {
		return appDomain;
	}

	/**
	 * 获取Servlet上下文对象
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		return servletContext;
	}
}
