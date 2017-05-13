package ke.alphacba.cms.core.base.pojo;

public class ErrorMsg {
	private int errorNo;
	private String errorMsg;
	private Long gmtCreate;
	private Long gmtModify;
	private Boolean isDelete;
	
	public int getErrorNo() {
		return errorNo;
	}
	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
}