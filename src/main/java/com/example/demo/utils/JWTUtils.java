package com.example.demo.utils;

import com.example.demo.pojo.Emp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {
    
    private static final String SECRET_KEY = "talis-system-secret-key";
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24小时
    
    /**
     * 生成JWT令牌
     * @param emp 员工对象
     * @return JWT令牌字符串
     */
    public static String generateToken(Emp emp) {
        // 设置过期时间
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        // 创建JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", emp.getId());
        claims.put("username", emp.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(emp.getUsername())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    
    /**
     * 验证并解析JWT令牌
     * @param token JWT令牌
     * @return 解析后的Claims对象
     * @throws SignatureException 当令牌签名无效时抛出异常
     */
    public static Claims parseToken(String token) throws SignatureException {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new SignatureException("无效的JWT签名", e);
        } catch (Exception e) {
            throw new RuntimeException("JWT令牌解析失败", e);
        }
    }
    
    /**
     * 验证JWT令牌是否有效
     * @param token JWT令牌
     * @return 有效返回true，否则返回false
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}