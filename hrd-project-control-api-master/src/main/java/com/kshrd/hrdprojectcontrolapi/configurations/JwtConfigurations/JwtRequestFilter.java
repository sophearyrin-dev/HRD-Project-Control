package com.kshrd.hrdprojectcontrolapi.configurations.JwtConfigurations;

import com.kshrd.hrdprojectcontrolapi.services.users.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = httpServletRequest.getHeader("Authorization");
        String username =null;
        String jwtToken = null;


        //jwt Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if(requestHeader!=null && requestHeader.startsWith("Bearer")){
            jwtToken = requestHeader.substring(7);
            try{
                username=jwtTokenUtil.getUsernameFromToken(jwtToken);
            }catch (IllegalArgumentException e){
                System.out.println("Unable to get JWT Token");

            }catch (ExpiredJwtException e){
                System.out.println("JWT Token has Expired");
            }
        }else {
            logger.warn("JWT token does not begin with Bearer String");
        }

        //Once we get the token validate it
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null ){
            UserDetails userDetails= userService.loadUserByUsername(username);

            //if token is valid configure spring security to manually set authentication
            if(jwtTokenUtil.validateToken(jwtToken,userDetails)){
                Object userDetails1;
                Object principal;
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));


                //After setting the authentication in the context , we specify that the current user is authenticated. so it passes the
                //spring security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
