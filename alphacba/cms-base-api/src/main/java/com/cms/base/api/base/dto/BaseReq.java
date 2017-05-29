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
package com.cms.base.api.base.dto;

import java.io.Serializable;

public class BaseReq implements Serializable{

	private String headUserId;
	private String headUserName;
	private String loginIp;
	public String getHeadUserId() {
		return headUserId;
	}
	public void setHeadUserId(String headUserId) {
		this.headUserId = headUserId;
	}
	public String getHeadUserName() {
		return headUserName;
	}
	public void setHeadUserName(String headUserName) {
		this.headUserName = headUserName;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
}
