package ke.alphacba.cms.core.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ke.alphacba.cms.core.util.HttpUtils;

/**
 * @author alphacba
 * 
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);
	/*不用权限过滤的URL*/
	private List<String> excludedUrl;
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("info:" + request.getRequestURL());
		excludedUrl.stream().forEach((str)->logger.info("url:" + str.toString()));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		modelAndView.addObject("BASE_PATH", basePath);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public List<String> getExcludedUrl() {
		return excludedUrl;
	}
	
	public void setExcludedUrl(List<String> excludedUrl) {
		this.excludedUrl = excludedUrl;
	}
}
