package com.quang.spring.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      super.postHandle(request, response, handler, modelAndView);
   }

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      System.out.println(request.getHttpServletMapping().getMappingMatch());
      return super.preHandle(request, response, handler);
   }
}