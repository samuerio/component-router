package com.issun.component.router.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;

/**
 * Gson工具方法类
 * 
 * @author ZHe
 *
 */
public class GsonUtil {
	
	//------------------------------------------------------- Static Variables

	private static final String DEFAULT_CHARSET = "UTF-8";
	
	private static final String MIME = "text/html;charset=" + DEFAULT_CHARSET;
	
	private static final Log LOG = LogFactory.getLog(GsonUtil.class);
	
	//------------------------------------------------------- Public Methods

	/**
	 * 将bean打印到Response的内容
	 * @param response
	 * @param bean
	 */
	public static void printJsonBean(HttpServletResponse response, Object bean) {
		if(null == bean){
			return;
		}
		
		Gson gson = new Gson();
		response.setContentType(MIME);
		response.setCharacterEncoding(DEFAULT_CHARSET);
		
		try {
			response.getWriter().write(gson.toJson(bean));
		} catch (IOException e) {
			//TODO 错误日志
			LOG.error(e);
			e.printStackTrace();
		}
	}

}
