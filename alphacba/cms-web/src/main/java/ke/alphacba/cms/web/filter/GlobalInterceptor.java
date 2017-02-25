package ke.alphacba.cms.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

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

}
