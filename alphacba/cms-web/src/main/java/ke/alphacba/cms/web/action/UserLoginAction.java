package ke.alphacba.cms.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.base.api.pojo.UserInfo;

@Controller
@RequestMapping("/")
public class UserLoginAction {
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login(Model model) {
		return "/manage/login";
	}

	@RequestMapping(value = "login.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> doLogin(UserInfo userInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		map.put("accessGranted", "true");
		Cookie cookie = new Cookie("token", "kexianjun");
		response.addCookie(cookie);
		return map;
	}
}
