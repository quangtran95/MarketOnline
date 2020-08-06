package com.quang.spring.demo.filter;

import com.quang.spring.demo.service.UserService;
import com.quang.spring.demo.utils.TokenAuthenticationUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

   private UserService userService;
   public JWTAuthenticationFilter(UserService userService) {
      this.userService = userService;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

      final String requestTokenHeader = httpServletRequest.getHeader("Authorization");

      String username = null;
      String jwtToken = null;
      // JWT Token is in the form "Bearer token". Remove Bearer word and get
      //  only the Token
      if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
         jwtToken = requestTokenHeader.substring(7);
         try {
            username = TokenAuthenticationUtils.getUsernameFromToken(jwtToken);
         } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
         } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
         }
      } else {
         logger.warn("JWT Token does not begin with Bearer String");
      }

      // Once we get the token validate it.
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            // if token is valid configure Spring Security to manually set
            // authentication
            if (TokenAuthenticationUtils.validateToken(jwtToken, userDetails)) {
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
               // After setting the Authentication in the context, we specify
               // that the current user is authenticated. So it passes the
               // Spring Security Configurations successfully.
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
         }
         catch (Exception ex){
            System.out.println("Authentication fail.");
         }
      }
      System.out.println("JWTAuthenticationFilter.doFilterInternal");
      filterChain.doFilter(httpServletRequest, httpServletResponse);
   }

}
