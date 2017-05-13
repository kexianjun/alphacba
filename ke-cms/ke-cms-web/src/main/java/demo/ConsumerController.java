package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ke.cms.base.demo.UserInfo;

@Controller
public class ConsumerController {
	@Autowired
	private RestTemplate restTemplate;
	@RequestMapping("/index")
	public String add(Model model){
		String res =
				restTemplate.getForEntity("http://KE-CMS-SERVICE/add?a=10&b=20", String.class).getBody();
		model.addAttribute("result", res);
		return "index";
	}
	
	@RequestMapping("userinfo")
	public String getUserInfo(Model model){
		UserInfo userInfo = null;
		userInfo = restTemplate.getForEntity("http://KE-CMS-SERVICE/getUserInfo?userName=柯贤俊", UserInfo.class).getBody();
		model.addAttribute("userinfo", userInfo);
		return "userinfo";
	}

}
