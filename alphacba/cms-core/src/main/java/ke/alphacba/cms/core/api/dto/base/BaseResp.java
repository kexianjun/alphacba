package ke.alphacba.cms.core.api.dto.base;

import java.io.Serializable;

import ke.alphacba.cms.core.meta.MetaDataService;

public class BaseResp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4419568073172066375L;
	private int errorNo = 0;
	private String errorInfo = "操作成功";
	
	public boolean isError(){
		return errorNo != 0;
	}
	
	public boolean isSuccess(){
		return !isError();
	}
	
	public int getErrorNo() {
		return errorNo;
	}
	public void setErrorNo(int errorNo, Object ...objects) {
		this.errorNo = errorNo;
		setErrorInfo(MetaDataService.getErrorInfo(errorNo, objects));
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
