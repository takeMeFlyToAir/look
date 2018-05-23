package com.zzr.web.interceptor;

import com.zzr.util.shiro.auth.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanxiaoping on 16/2/28.
 */
@Slf4j
public class PageInfoHandlerInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
		if(UserUtil.getCurrentUser()  != null){
			request.setAttribute("currentUser",UserUtil.getCurrentUser());
		}
	}
}
