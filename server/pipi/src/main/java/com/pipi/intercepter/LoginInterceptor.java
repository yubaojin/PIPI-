package com.pipi.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * @author Administrator
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String username = (String) request.getSession().getAttribute("username");
		System.out.println("++++++++++++" + username);
		System.out.println(request.getServletPath());
		//如果访问/也就是登录页面 先判断是否登录 如果登录 就直接跳转到主页
		if (request.getServletPath().equals("/")) {
			if (StringUtils.isNotBlank(username)) {
				response.sendRedirect("/toIndex");
			}
			return true;
		}
		//如果不为/怎判断是否登录
		if (!StringUtils.isNotBlank(username)) {
			response.sendRedirect("/");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
