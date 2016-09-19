# Router-Conponent

基于Struct封装，简单、好用的路由组件

# Features

- 提供请求路径的统一管理路由
- 通过配置文件将请求分发到对应Action实现类

# Quick Start

**1.在struct.xml配置路由**

```xml
<action name="app"
	class="com.issun.component.router.AppCommonAction">
	<interceptor-ref name="defaultStack"/>
	<result name="success">/success.jsp</result>
  <result name="fail">/fail.jsp</result>
</action>	
```

**2.在WEB-INF/config/action路径下面提供action映射的配置文件verbs.properties**

eg.

```properties
formDesignAction =com.issun.formdesigner.action.FormDesignAction
```

**3.想通过路由管理的Action实现IAction接口,并在verbs.properties配置映射参数**

eg.

```java
package com.issun.formdesigner.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.issun.component.router.IAction;
import com.issun.component.router.bean.ActionInfo;
import com.issun.component.router.bean.ActionResult;

public class FormDesignAction implements IAction {

	@Override
	public ActionResult execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		ActionResult ar = new ActionResult();
		
		//......
		
		return ar;
	}

}
```


```properties
formDesignAction =com.issun.formdesigner.action.FormDesignAction
```

那么以后带有`app.action?type=formDesignAction`的请求都会路由到FormDesignAction。




