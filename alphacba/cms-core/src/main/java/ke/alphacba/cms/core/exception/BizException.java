package ke.alphacba.cms.core.exception;

import org.apache.commons.lang3.StringUtils;

public class BizException extends Exception {

	private static final long serialVersionUID = -3590903052532718056L;
	
	private String exceptionMsg = "";
	
	private String errorCode = "0000";
	public BizException(){
	}
	
	public BizException(String exceptionMsg){
		this(null, exceptionMsg);
	}
	
	public BizException(String errorCode, String errorMsg){
		if (StringUtils.isNotBlank(errorCode)) {
			this.errorCode = errorCode;
		}
		if (StringUtils.isNotBlank(errorMsg)) {
			this.exceptionMsg = errorMsg;
		}
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
