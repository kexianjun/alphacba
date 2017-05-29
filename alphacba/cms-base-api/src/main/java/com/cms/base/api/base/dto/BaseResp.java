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

import org.codehaus.jackson.annotate.JsonIgnore;

public class BaseResp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2545763479993382120L;
	private static final int OK_SUCCESS = 0;
	private Integer errorNo = 0;
	private String errorInfo = "操作成功!";
	private String serviceIp;

	/**
	 * 对象转json，用于hsadmin使用xml配置paramJson转换成对象时对应的属性名称
	 */
	private String objectParamName;
	/**
	 * 对象转json,用于参数转json，不为空时替换param参数对象
	 */
	private String paramJson;
	/**
	 * 对象转json，用于结果转json
	 */
	private String resultJson;
	
	public Integer getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(Integer errorNo) {
		this.setErrorNo(errorNo, new Object[] {});
	}

	public void setErrorNo(Integer errorNo, Object... params) {
		this.errorNo = errorNo;
		//this.setErrorInfo(MetadataService.getErrorInfo(errorNo, params));
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	
	public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    /**
	 * 判断是否出错<br>
	 * true - 出错<br>
	 * false - 未出错
	 * 
	 * @return b
	 */
	@JsonIgnore
	public boolean isError() {
		return this.errorNo != OK_SUCCESS;
	}

	/**
	 * 判断是否成功<br>
	 * true - 成功<br>
	 * false - 未成功
	 * 
	 * @return b
	 */
	@JsonIgnore
	public boolean isSuccess() {
		return !this.isError();
	}

	/**
	 * 判断是否是系统错误
	 */
	@JsonIgnore
	public boolean isSysError() {
		return this.errorNo < 0;
	}


	public String getParamJson() {
		return paramJson;
	}

	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
	
	public String getObjectParamName() {
		return objectParamName;
	}

	public void setObjectParamName(String objectParamName) {
		this.objectParamName = objectParamName;
	}	  
}
