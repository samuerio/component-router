package com.issun.component.router.bean;

/**
 * ActionResult为IAtion返回数据的封装(包括重定向、转发、返回数据/提示信息)
 * 
 * @author ZHe
 */
public class ActionResult {
	
	//------------------------------------------------------- Instance Variables
	
	/**
	 * 是否重定向的标识
	 */
	private boolean isRedirect;
	
	/**
	 * 当isRedirect为true时，有值
	 */
	private ActionForward actionForward;
	
	/**
	 * 当isRedirect为false时，有值
	 */
	private ActionInfo actionInfo;
	
	
	//------------------------------------------------------- Public Methods
	
	public boolean isRedirect() {
		return isRedirect;
	}
	
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	public ActionForward getActionForward() {
		return actionForward;
	}
	
	public void setActionForward(ActionForward actionForward) {
		this.actionForward = actionForward;
	}
	
	public ActionInfo getActionInfo() {
		return actionInfo;
	}
	
	public void setActionInfo(ActionInfo actionInfo) {
		this.actionInfo = actionInfo;
	}
}
