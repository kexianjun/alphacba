package ke.alphacba.cms.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebCommonAction {
	@RequestMapping("index.htm")
	public String index(Model model){
		return "/manage/index";
	}
	
	
	@RequestMapping("pagecontent.htm")
	public String pageContent(Model model){
		return "/manage/pagecontent";
		
	}
}
