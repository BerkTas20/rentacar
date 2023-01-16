package com.berktas.rentacar.core.security;


import com.berktas.rentacar.business.concretes.UserDetailsManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Autowired
    private UserDetailsManager userDetailsManager;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private void doWork(HttpServletRequest req, HttpServletResponse res) {

        //session already authenticated
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        //header empty
        String header = req.getHeader(HEADER_STRING);
        if (header == null) {
            return;
        }

        //token prefix control
        if (!header.startsWith(TOKEN_PREFIX)) {
            return;
        }

        //extract token
        String authToken = header.replace(TOKEN_PREFIX, "");
        JwtToken jwtToken = JwtToken.parse(authToken);

        //load userdetails
        String username = jwtToken.getClaims().get(JwtTokenUtil.KEY_USERNAME, String.class);
        CustomUserDetails userDetails = userDetailsManager.loadUserByUsername(username);

        //userdetails not found
        if (userDetails == null) {
            return;
        }
        //check validity
        if (jwtTokenUtil.isValid(jwtToken, userDetails)) {
            //set security
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        try {
            doWork(req, res);
        } catch (Exception e) {
            e.getCause();
        }

        chain.doFilter(req, res);
    }

}