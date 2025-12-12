package com.example.demo.filter;

import com.example.demo.pojo.Result;
import com.example.demo.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取请求路径
        String uri = httpRequest.getRequestURI();

        // 白名单路径（不需要登录验证的路径）
        if (uri.contains("/login") || uri.contains("/error")) {
            chain.doFilter(request, response);
            return;
        }

        // 从请求头中获取token
        String token = httpRequest.getHeader("Authorization");

        // 如果没有token，则返回错误
        if (token == null || token.isEmpty()) {
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpResponse.setContentType("application/json;charset=utf-8");
            Result result = Result.error("未提供访问令牌");
            httpResponse.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }

        try {
            // 验证并解析token
            Claims claims = JWTUtils.parseToken(token);
            
            // 将用户信息放入request属性中供后续使用
            httpRequest.setAttribute("userId", claims.get("id"));
            httpRequest.setAttribute("username", claims.get("username"));

            // token有效，继续执行
            chain.doFilter(request, response);
        } catch (SignatureException e) {
            // token无效
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpResponse.setContentType("application/json;charset=utf-8");
            Result result = Result.error("无效的访问令牌");
            httpResponse.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }
}