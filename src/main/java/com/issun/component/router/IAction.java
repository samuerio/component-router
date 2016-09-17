package com.issun.component.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.issun.component.router.bean.ActionResult;


/**
 * 路由对外提供的Action实现接口
 * 
 * @author ZHe
 *
 */
public interface IAction {
	
	//------------------------------------------------------- Public Methods
	
	public ActionResult execute(HttpServletRequest request,
			HttpServletResponse response);
}
