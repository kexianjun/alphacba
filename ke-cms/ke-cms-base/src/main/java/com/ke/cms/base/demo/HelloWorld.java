package com.ke.cms.base.demo;

public class HelloWorld {
	public String sayHello(){
		return "from base hello ";
	}
	
	public UserInfo getUserInfo(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("1234556");
		userInfo.setUserName("柯贤俊");
		userInfo.setPassword("foregye");
		
		return userInfo;
	}
}
