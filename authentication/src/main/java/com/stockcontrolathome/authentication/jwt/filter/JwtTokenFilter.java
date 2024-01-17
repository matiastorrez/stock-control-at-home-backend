package com.stockcontrolathome.authentication.jwt.filter;


import com.stockcontrolathome.authentication.jwt.service.JwtService;
import com.stockcontrolathome.authentication.service.impl.UserDetailServiceImpl;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer";

    @Autowired
    private JwtService jwtProvider;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Estoy en filter");

        try {

            String token = getJwtFromRequest(request);

            // validamos el token
            if (token != null && jwtProvider.validateToken(token)) {

                String email = jwtProvider.getEmail(token);

                UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request, response);

        }catch (JwtException | AuthenticationException ja){
            log.info("Estoy en JTWTokenFilter: " + ja.getMessage());
            resolver.resolveException(request, response, null, new JwtException("Problemas con el inicio de sesion, borre el token del encabezado e inicie sesion nuevamente"));
        }
    }

    // Bearer token de acceso
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
