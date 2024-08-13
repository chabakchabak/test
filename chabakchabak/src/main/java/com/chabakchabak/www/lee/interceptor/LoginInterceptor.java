package com.chabakchabak.www.lee.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("LoginInterceptor..................................");
		Object obj = modelAndView.getModel().get("login");
		log.info("obj : " + obj);
		if(obj==null) {
			modelAndView.setViewName("redirect:/");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginSessionDto", obj);
			String location = "/";
	
			String targetLocation = (String)session.getAttribute("targetLocation");
			log.info("targetLocation : " + targetLocation);

			if(targetLocation != null ) {
				// 이동할때
				log.info("타겟 not널");
				int displyIndex = targetLocation.indexOf("/display");
				log.info("displyIndex : " + displyIndex);
				if((displyIndex > -1)) {
					location = (String)session.getAttribute("curLocation");
					
				}else {
					location = targetLocation;
				}
				if(location.equals("/lee/user/profile")) {
					location = "/";
				}
			} 
			else {
				//가만히 있을때
				log.info("타겟 널");
				location = (String)session.getAttribute("curLocation");
				log.info(location);
			}
			log.info("전송전 location : " + location);
			modelAndView.setViewName("redirect:" + location);
		}
	}
}
