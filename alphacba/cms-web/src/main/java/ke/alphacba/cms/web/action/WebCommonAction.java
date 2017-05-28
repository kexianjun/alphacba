package ke.alphacba.cms.web.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.pojo.UserInfo;

import ke.alphacba.cms.core.cache.SysCacheService;
import ke.alphacba.cms.core.rpc.CMSServiceRpc;

@Controller
@RequestMapping("/")
public class WebCommonAction {
	
	@Autowired
	private SysCacheService sysCacheService;
	@Autowired
	private CMSServiceRpc cmsServiceRpc;
	
	@RequestMapping("pagecontent.htm")
	public String pageContent(Model model){
		String helloStr = null;
		try{
			helloStr = cmsServiceRpc.helloWorld("GOD");
		}catch (Exception e) {
			
		}
		if (StringUtils.isEmpty(helloStr)) {
			helloStr = "rpc failed";
		}
		final String keyf = "mykey";
		String retSring = null;
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("kexianjun");
		userInfo.setUserPhone("123456677");
		userInfo.setUserPassword("forget-me-not");
		boolean result = sysCacheService.saveObject(keyf, userInfo);
		UserInfo redisReture = sysCacheService.getObject(keyf, userInfo.getClass());
		UserInfoReq userInfoReq = new UserInfoReq();
		userInfoReq.setParams(redisReture);
		UserInfoResp userInfoResp = cmsServiceRpc.getUserInfo(userInfoReq);
		if (userInfoResp.getRespItem() != null) {
			model.addAttribute("myKey", userInfoResp.getRespItem());
			
		}
		model.addAttribute("hello",helloStr);
		model.addAttribute("userInfo", userInfoResp.getRespItem());
		return "/manage/pagecontent";
		
	}
	

}
