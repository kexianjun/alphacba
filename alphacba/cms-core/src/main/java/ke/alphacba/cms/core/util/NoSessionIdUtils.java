/**
 * 
 * - Copyright 2016-2017 alphacba.
 * - Licensed under the Apache License, Version 2.0 (the "License");
 * - you may not use this file except in compliance with the License.
 * - You may obtain a copy of the License at
 * -    http://www.apache.org/licenses/LICENSE-2.0
 * - Unless required by applicable law or agreed to in writing, software
 * - distributed under the License is distributed on an "AS IS" BASIS,
 * - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * - See the License for the specific language governing permissions and
 * - limitations under the License. 
 */
package ke.alphacba.cms.core.util;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cms.base.api.base.dto.BaseResp;

import ke.alphacba.cms.core.constant.ErrorNoConstants;

public class NoSessionIdUtils {
protected final static Logger log = LoggerFactory.getLogger(NoSessionIdUtils.class);
	

	public static String getNoSessionId(Cookie[] cookies, String loginCookieName) {
		if (cookies == null) {
			return "";
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(loginCookieName)) {
				return cookies[i].getValue();
			}
		}
		log.error("sessionid标识符[" + loginCookieName + "]不存在！");
		return "";
	}
	//生成登录cookieId
	public static BaseResp writeCookie(HttpSession session,HttpServletRequest request,HttpServletResponse response,String loginCookieName,String domain,int cookieTime) {
		       BaseResp baseResp = new BaseResp();
		       String jsessionId = 	(String) session.getAttribute("jsessionId");
		   try {
			   Cookie [] cookies =   request.getCookies();
			   if (cookies == null) {
				   baseResp.setErrorNo(ErrorNoConstants.ERR_COM_ERRORINFO,"获取会话失败");
				   return baseResp;
				}
			   boolean flag = false;
			   boolean httpOnly = true;
			   for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals(loginCookieName)) {
						 if(StringUtils.isEmpty(cookies[i].getValue())){
						 writeCookie(response, loginCookieName, domain, cookieTime, jsessionId, httpOnly);
					     } 
				      flag = true;	
				    }
				}
			   if(!flag){
				     writeCookie(response, loginCookieName, domain, cookieTime, jsessionId, httpOnly);  
			   }
		   } catch (Exception e) {
		     log.error("write cookie store error !", e);
	    }
		   return baseResp;
	}
	private static void writeCookie(HttpServletResponse response, String loginCookieName, String domain, int cookieTime,
			String jsessionId, boolean httpOnly) {
	
		 String  cookieValue =  new String(Base64.encodeBase64(new StringBuilder().append(loginCookieName).append(new Date().getTime()).append(jsessionId).toString().getBytes()));
		 Cookie cookie1 = new Cookie(loginCookieName, cookieValue);
		 cookie1.setHttpOnly(true);
		 cookie1.setMaxAge(cookieTime);
		 cookie1.setPath("/");
		 cookie1.setDomain(domain);
		 response.addCookie(cookie1);
	}
}
