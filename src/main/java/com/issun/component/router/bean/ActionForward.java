package com.issun.component.router.bean;

/**
 * Action重定向信息包裹对象
 * 
 * @author ZHe
 *
 */
public class ActionForward {
	
	//------------------------------------------------------- Instance Variables
	
	private boolean isUrl;
	
	private String result;
	
	//------------------------------------------------------- Public Methods
	
	public boolean isUrl() {
		return isUrl;
	}
	
	public void setUrl(boolean isUrl) {
		this.isUrl = isUrl;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
}
