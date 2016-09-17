package com.issun.component.router.bean;

/**
 * Action信息包裹对象
 * 
 * @author ZHe
 *
 */
public class ActionInfo {
	
	//------------------------------------------------------- Instance Variables
	
	/**
	 * Action请求是否成功标识
	 */
	private boolean isSuccess;
	
	/**
	 * isSuccess为false时，有值
	 */
	private String message;
	
	/**
	 * isSuccess为true时，有值
	 */
	private Object content;
	
	//------------------------------------------------------- Public Methods
	
	public boolean isSuccess() {
		return isSuccess;
	}
	
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getContent() {
		return content;
	}
	
	public void setContent(Object content) {
		this.content = content;
	}
	
}
