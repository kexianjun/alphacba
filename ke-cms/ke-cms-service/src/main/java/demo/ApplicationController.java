package demo;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ke.cms.base.demo.UserInfo;

@RestController
public class ApplicationController {
	private final Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        Integer r = a + b;
        logger.info("/add, result:" + r);
        return r;
    }
    
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public UserInfo getUserInfo(@RequestParam String userName){
    	UserInfo userInfo = new UserInfo();
    	userInfo.setUserName(userName);
    	userInfo.setUserId("123445567");
    	userInfo.setPassword("foregefdf");
    	return userInfo;
    }
}
