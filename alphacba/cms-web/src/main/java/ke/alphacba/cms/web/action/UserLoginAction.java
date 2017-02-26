package ke.alphacba.cms.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/")
public class UserLoginAction {
	@RequestMapping(value="login.htm",method=RequestMethod.GET)
	public String login(Model model){
		return "/manage/login";
	}
}
