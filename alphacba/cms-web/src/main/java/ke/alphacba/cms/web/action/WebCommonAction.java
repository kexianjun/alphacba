package ke.alphacba.cms.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ke.alphacba.cms.core.rpc.CMSServiceRpc;

@Controller
@RequestMapping("/")
public class WebCommonAction {
	@Autowired
	private CMSServiceRpc cmsServiceRpc;
	@RequestMapping("index.htm")
	public String index(Model model){
		return "/manage/index";
	}
	
	
	@RequestMapping("pagecontent.htm")
	public String pageContent(Model model){
		String helloStr = cmsServiceRpc.helloWorld("GOD");
		model.addAttribute("hello",helloStr);
		return "/manage/pagecontent";
		
	}
}
