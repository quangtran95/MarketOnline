package com.quang.spring.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TokenAuthenticationUtils implements Serializable {
   static final long EXPIRATIONTIME = 864_000_000; // 10 days

   static final String SECRET = "ThisIsASecret";

   static final String TOKEN_PREFIX = "Bearer";

   static final String HEADER_STRING = "Authorization";

   public static final long JWT_TOKEN_VALIDITY = 1 * 24 * 60 * 60 * 1000; //day * hour * minute * second * millisecond


   public static void addAuthentication(HttpServletResponse res, String username) {
      String JWT = Jwts.builder().setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, SECRET).compact();
      res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
   }

   public static Authentication getAuthentication(HttpServletRequest request) {
      String token = request.getHeader(HEADER_STRING);
      if (token != null) {
         // parse the token.
         String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
               .getSubject();

         return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
      }
      return null;
   }

   //retrieve username from jwt token
   public static String getUsernameFromToken(String token) {
      return getClaimFromToken(token, Claims::getSubject);
   }
   //retrieve expiration date from jwt token
   public static Date getExpirationDateFromToken(String token) {
      return getClaimFromToken(token, Claims::getExpiration);
   }
   public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = getAllClaimsFromToken(token);
      return claimsResolver.apply(claims);
   }
   //for retrieveing any information from token we will need the secret key
   private static Claims getAllClaimsFromToken(String token) {
      return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
   }
   //check if the token has expired
   private static Boolean isTokenExpired(String token) {
      final Date expiration = getExpirationDateFromToken(token);
      return expiration.before(new Date());
   }
   //generate token for user
   public static String generateToken(UserDetails userDetails) {
      Map<String, Object> claims = new HashMap<>();
      return doGenerateToken(claims, userDetails.getUsername());
   }
   //while creating the token -
//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//2. Sign the JWT using the HS512 algorithm and secret key.
//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//   compaction of the JWT to a URL-safe string
   private static String doGenerateToken(Map<String, Object> claims, String subject) {
      return TOKEN_PREFIX + " " + Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
            .signWith(SignatureAlgorithm.HS512, SECRET).compact();
   }
   //validate token
   public static Boolean validateToken(String token, UserDetails userDetails) {
      final String username = getUsernameFromToken(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }
}
