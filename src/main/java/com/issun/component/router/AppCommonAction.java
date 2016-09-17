package com.issun.component.router;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.issun.component.router.bean.ActionForward;
import com.issun.component.router.bean.ActionInfo;
import com.issun.component.router.bean.ActionResult;
import com.issun.component.router.util.GsonUtil;
import com.issun.component.router.util.PropertyBase;
import com.issun.component.router.util.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 路由控制
 * 
 * @author ZHe
 */
public class AppCommonAction extends ActionSupport 
		implements ServletRequestAware,ServletResponseAware{
	
	//------------------------------------------------------- Static Variables
	
	private static final long serialVersionUID = -5051483770823538130L;

	private static final String DEFAULT_FORWARD = "forward";

	/**
	 * Action配置文件名称
	 */
	private static final String ACTION_CONFIG_NAME = "verbs.properties";
	
	/**
	 * Action配置文件地址
	 */
	private static final String ACTION_CONFIG_PATH = 
			WebConstants.getWebInfPath() + File.separator + "config" + File.separator + "action";

	
	//------------------------------------------------------- Instance Variables
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	
	//------------------------------------------------------- Public Methods

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public String execute() throws Exception {
		
		boolean isSuccess = false;
		String message = "";
		String result = "";
		
		
		String type = this.request.getParameter("type");
		
		if(StringUtils.isNotEmpty(type)){
			
			String actionCls = this.getActionClass(type);
			if(StringUtils.isEmpty(actionCls)){
				message = "无法根据type="+type+"而获取到相应的实现类！";
				isSuccess = false;
				this.printMessage(isSuccess,message);
				return result;
			}
			
			IAction action = (IAction)Class.forName(actionCls).newInstance();
			ActionResult actionResult = action.execute(request,response);
			
			if(null != actionResult){
				if(actionResult.isRedirect()){//若为URL方式，直接导向
					ActionForward af = actionResult.getActionForward();
					
					if(null != af){
						if(!af.isUrl()){
							result = af.getResult();
						}else{
							//TODO struts如何实现跳转
							result = DEFAULT_FORWARD;
							request.setAttribute(DEFAULT_FORWARD, af.getResult());
						}
					}
				}else{//若为消息格式，根据消息对象直接打印
					ActionInfo ai = actionResult.getActionInfo();
					GsonUtil.printJsonBean(response,ai);
				}
			}
		}
		
		
		return result;
	}
	
	//------------------------------------------------------- Private Methods

	/**
	 * 打印信息到Response
	 * @param isSuccess
	 * @param message
	 */
	private void printMessage(boolean isSuccess, String message) {
		ActionInfo actionInfo = new ActionInfo();
		
		actionInfo.setSuccess(isSuccess);
		actionInfo.setMessage(message);
		GsonUtil.printJsonBean(response, actionInfo);
	}

	/**
	 * 通过Action配置文件获取对应类的名称
	 * @param type
	 * @return className
	 */
	private String getActionClass(String type) {
		String actionCls = "";
		
		String propertyFilePath = ACTION_CONFIG_PATH + File.separator
				+ ACTION_CONFIG_NAME;
		PropertyBase pb = PropertyBase.getInstance(propertyFilePath);
		actionCls = pb.getValue(type);
		
		return actionCls;
	}

}
