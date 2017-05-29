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

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestContextUtils;

import ke.alphacba.cms.core.constant.CoreConstants;

public class RequestUtils extends RequestContextUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
	private static final FastDateFormat FDF = FastDateFormat.getInstance(CoreConstants.YYYYMMDDHHMMSS);

	/**
	 * 提取请求参数值到Map并返回.
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<String, String>();
		Enumeration<String> enume = request.getParameterNames();
		while (enume.hasMoreElements()) {
			String paramName = enume.nextElement();
			paramMap.put(paramName, request.getParameter(paramName));
		}
		return paramMap;
	}

	/**
	 * 判断是否当前请求是否接受Json数据响应
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAcceptJson(HttpServletRequest request) {
		return request.getRequestURI().contains(".json") || request.getHeader(CoreConstants.ACCEPT).indexOf(CoreConstants.APPLICATION_JSON) > -1;
	}

	/**
	 * 根据后缀,获取请求的uri体
	 * 
	 * @param suffix
	 * @return
	 */
	public static String getUriBody(HttpServletRequest request, String suffix) {
		return request.getRequestURI().replace(request.getContextPath() + "/", "").replace(suffix, "");
	}

	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (isBlank(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isBlank(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isBlank(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private static boolean isBlank(String ip) {
		return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
	}

	/**
	 * 处理文件名称乱码
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String encodeFileName(HttpServletRequest request, String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			try {
				String userAgent = request.getHeader(CoreConstants.USER_AGENT).toUpperCase();
				if (userAgent.indexOf(CoreConstants.MSIE) != -1) {// IE
					fileName = URLEncoder.encode(fileName, CoreConstants.UTF_8);
				} else if (userAgent.indexOf(CoreConstants.FIREFOX) != -1 || userAgent.indexOf(CoreConstants.CHROME) != -1) {// FIREFOX
					fileName = new String(fileName.getBytes(CoreConstants.UTF_8), CoreConstants.ISO_8859_1);
				} else {
					throw new RuntimeException("目前只支持浏览器有IE、火狐以及谷歌!");
				}
			} catch (Exception e) {
				LOGGER.error("文件名[" + fileName + "]转换失败", e);
			}
		} else {
			fileName = "";
		}
		return fileName + "_" + FDF.format(new Date());
	}

	public static String getJSONString(HttpServletRequest request) {
		String CHARSET = "UTF-8";
		String json = "";
		try {
			ServletInputStream in = request.getInputStream();
			String content = IOUtils.toString(in, CHARSET);
			json = URLDecoder.decode(content, CHARSET);
			json = json.substring(json.indexOf("=") + 1);
		} catch (IOException e) {
			
		}
		return json;
	}
}
