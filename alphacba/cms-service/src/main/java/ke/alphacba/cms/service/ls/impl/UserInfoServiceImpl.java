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
package ke.alphacba.cms.service.ls.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.base.api.constants.ErrorNoConstants;
import com.cms.base.api.dto.UserInfoReq;
import com.cms.base.api.dto.UserInfoResp;
import com.cms.base.api.pojo.UserInfo;

import ke.alphacba.cms.service.as.UserInfoAtomService;
import ke.alphacba.cms.service.ls.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoAtomService userInfoAtomService;
	@Override
	public UserInfoResp userLogin(UserInfoReq req) {
		UserInfoResp userInfoResp = new UserInfoResp();;
		if (null == req || null == req.getParams()) {
			userInfoResp.setErrorNo(ErrorNoConstants.PARAMS_EMPTY_ERROR);
			return userInfoResp;
		}
		String userId = req.getParams().getUserId();
		if (StringUtils.isBlank(userId)) {
			userInfoResp.setErrorNo(ErrorNoConstants.USER_ID_EMPTY_ERROR);
			return userInfoResp;
		}
		userInfoResp = getUserInfoByUserId(userId);
		if (userInfoResp.isError()) {
			return userInfoResp;
		}
		
		if (null == userInfoResp.getRespItem()) {
			userInfoResp.setErrorNo(new Integer(ErrorNoConstants.USER_NOT_EXIST_ERROR),req.getParams().getUserId());
			return userInfoResp;
		}
		UserInfo userInfo = userInfoResp.getRespItem();
		//校验用户密码是否正确
		if (!userInfo.getUserPassword().equals(req.getParams().getUserPassword())) {
			userInfoResp.setErrorNo(ErrorNoConstants.PARAMS_EMPTY_ERROR);
			return userInfoResp;
		}
		return userInfoResp;
	}
	@Override
	public UserInfoResp getUserInfoByUserId(String userId){
		UserInfoResp userInfoResp = null;
		if (StringUtils.isEmpty(userId)) {
			userInfoResp = new UserInfoResp();
			userInfoResp.setErrorNo(ErrorNoConstants.PARAMS_EMPTY_ERROR);
			return userInfoResp;
		}
		UserInfoReq userInfoReq = new UserInfoReq();
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		userInfoReq.setParams(userInfo);
		userInfoResp = userInfoAtomService.getUserInfo(userInfoReq);
		return userInfoResp;
	}

}
