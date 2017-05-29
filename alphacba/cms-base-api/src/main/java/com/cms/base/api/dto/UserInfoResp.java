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
package com.cms.base.api.dto;


import java.io.Serializable;
import java.util.List;

import com.cms.base.api.base.dto.BaseResp;
import com.cms.base.api.pojo.UserInfo;

public class UserInfoResp extends BaseResp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5860671397908548639L;

	private UserInfo respItem;
	
	private List<UserInfo> respItems;

	public UserInfo getRespItem() {
		return respItem;
	}

	public void setRespItem(UserInfo respItem) {
		this.respItem = respItem;
	}

	public List<UserInfo> getRespItems() {
		return respItems;
	}

	public void setRespItems(List<UserInfo> respItems) {
		this.respItems = respItems;
	}
	
	
}
