package com.issun.component.router.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Web应用的相关常量
 * 
 * @author ZHe
 *
 */
public class WebConstants {

	//------------------------------------------------------- Static Variables
	
	public static final String WEBINF = "WEB-INF";
	
	//------------------------------------------------------- Public Methods

	/**
	 * 获取Web应用的WEB-INF路径
	 * @return
	 */
	public static String getWebInfPath() {
		String pathInfo = WebConstants.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		String osName = System.getProperty("os.name");
		
		if(StringUtils.isNotEmpty(osName) && osName.startsWith("Windows")){
			//在JDK7前， pathInfo的前缀为/,JDK后pathInfo的前缀为file:/
			return pathInfo.substring(pathInfo.indexOf("/") + 1,pathInfo.toUpperCase()
					.lastIndexOf(WEBINF) + 7);
		}else{
			return pathInfo.substring(0,pathInfo.toUpperCase()
					.lastIndexOf(WEBINF) + 7);
		}
	}

}
