package ke.alphacba.cms.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SystemIndex {

	@RequestMapping("index.htm")
	public String index(Model model){
		return "/manage/index";
	}
}
