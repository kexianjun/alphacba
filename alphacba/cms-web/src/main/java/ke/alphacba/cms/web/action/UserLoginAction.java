package ke.alphacba.cms.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.base.api.base.dto.BaseResp;
import com.cms.base.api.base.pojo.UserAgent;
import com.cms.base.api.constants.ErrorNoConstants;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.pojo.UserInfo;
import com.cms.base.api.rpc.CMSServiceRpc;

import jxl.biff.BaseCellFeatures;
import ke.alphacba.cms.core.cache.SysCacheService;
import ke.alphacba.cms.core.meta.MetaDataService;
import ke.alphacba.cms.core.util.NoSessionIdUtils;

@Controller
@RequestMapping("/")
public class UserLoginAction {
	private static final Logger LOG = LoggerFactory.getLogger(UserLoginAction.class);
	@Value("${login.cookie.name}")
	private String loginCookieName;
	@Autowired
	private CMSServiceRpc cmsServiceRpc;
	
	
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login(Model model) {
		return "/manage/login";
	}

	@RequestMapping(value = "login.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doLogin(UserInfo userInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		String nosessionId = NoSessionIdUtils.getNoSessionId(request.getCookies(), loginCookieName);
		UserInfoReq userInfoReq = new UserInfoReq();
		userInfoReq.setParams(userInfo);
		UserInfoResp userInfoResp = cmsServiceRpc.userLogin(userInfoReq);
		if (userInfoResp.isError()) {//登陆校验失败
			MetaDataService.removeSessionObj(nosessionId, userInfo.getUserId());
			map.put("errors", "用户名或密码错误");
			map.put("accessGranted", false);
			return map;
		}
		
		/*
		*登陆成功
		*1,userAgent放到缓存里面去
		*/
		UserAgent userAgent = new UserAgent();
		userAgent.setSessionId(nosessionId);
		try {
			PropertyUtils.copyProperties(userAgent, userInfoResp.getRespItem());
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			LOG.error("longin error:", e);
			map.put("errors", "登陆失败");
			map.put("accessGranted", false);
			return map;
		}
		BaseResp baseResp = flushUserAgentToCache(userAgent);
		if (baseResp.isError()) {
			map.put("errors", baseResp.getErrorInfo());
			map.put("accessGranted", false);
			return map;
		}
		model.addAttribute("userAgent", userAgent);
		map.put("accessGranted", true);
		return map;
	}

	private BaseResp flushUserAgentToCache(UserAgent userAgent) {
		BaseResp baseResp = new BaseResp();
		try{
		MetaDataService.savaSessionObj(userAgent.getSessionId(), userAgent);
		}catch (Exception e) {
			LOG.error("error:", e);
			baseResp.setErrorNo(ErrorNoConstants.LOGIN_ERROR);
			return baseResp;
		}
		return baseResp;
	}
}
