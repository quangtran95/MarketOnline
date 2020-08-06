package com.quang.spring.demo.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends OncePerRequestFilter {
   @Override
   protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
      System.out.println("CorsFilter.doFilterInternal");

//      if(httpServletResponse.getHeader("Access-Control-Allow-Origin") == null) {
//         httpServletResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
////      }
//      httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
//      httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, authorization");
//      httpServletResponse.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
//      httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
//      httpServletResponse.addIntHeader("Access-Control-Max-Age", 10);
      filterChain.doFilter(httpServletRequest, httpServletResponse);
   }
}
