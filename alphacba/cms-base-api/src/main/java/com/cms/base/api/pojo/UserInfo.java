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
package com.cms.base.api.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = -4649551055583224957L;
	private String userId;
	private String userName;
	private String userPassword;
	private String userType;
	private String userStatus;
	private String loginIp;
	private String loginMac;
	private String lastLoginIp;
	private String lastLoginMac;
	private Long gmtCreate;
	private Long gmtModify;
	private String userEmail;
	private String userPhone;
	private String remark1;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginMac() {
		return loginMac;
	}

	public void setLoginMac(String loginMac) {
		this.loginMac = loginMac;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginMac() {
		return lastLoginMac;
	}

	public void setLastLoginMac(String lastLoginMac) {
		this.lastLoginMac = lastLoginMac;
	}

	public Long getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Long gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Long gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userType=" + userType + ", userStatus=" + userStatus + ", loginIp=" + loginIp + ", loginMac="
				+ loginMac + ", lastLoginIp=" + lastLoginIp + ", lastLoginMac=" + lastLoginMac + ", gmtCreate="
				+ gmtCreate + ", gmtModify=" + gmtModify + ", userEmail=" + userEmail + ", userPhone=" + userPhone
				+ ", remark1=" + remark1 + "]";
	}

}
