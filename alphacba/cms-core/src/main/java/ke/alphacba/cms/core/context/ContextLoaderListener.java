package ke.alphacba.cms.core.context;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener
implements ServletContextListener{
	public static final String CONFIG_LOCATION = "alphacbaConfigLocation";
	private static final Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);
	@Autowired
	private Environment environment;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		String configration = event.getServletContext().getInitParameter(CONFIG_LOCATION);
		String [] locations = configration.split(",");
		Resource resource;
		String propertyKey;
		String propertyValue;
		for(String location : locations){
			resource = new ClassPathResource(location);
			logger.info(resource.getFilename());
			try {
				Properties properties = new Properties();
				properties.load(resource.getInputStream());
				Enumeration<Object> keys = properties.keys();
				while (keys.hasMoreElements()) {
					propertyKey  = (String) keys.nextElement();
					propertyValue = properties.getProperty(propertyKey);
					System.setProperty(propertyKey, propertyValue);
				}
			} catch (IOException e) {
				logger.error("contextInitialized error:", e);
			}
			
		}
		System.setProperty(CONFIG_LOCATION, configration);
		super.contextInitialized(event);
	}
	
	@Override
	protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext applicationContext) {
		String[] configLocations = applicationContext.getConfigLocations();
	    if ((configLocations == null) || (configLocations.length < 1)) {
	      configLocations = StringUtils.tokenizeToStringArray(servletContext.getInitParameter("contextConfigLocation"), ",; \t\n");
	    }
	   
	    applicationContext.setConfigLocations(configLocations);
	    super.customizeContext(servletContext, applicationContext);
	}
}
