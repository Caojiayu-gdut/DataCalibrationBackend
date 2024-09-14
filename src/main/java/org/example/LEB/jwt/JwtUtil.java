package org.example.LEB.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "5f6c10a7f1eab7e9e0d5a8c2e0b8c5d7"; // Replace with your secret key
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    

    public static String generateToken(String username) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Token有效时间 (这里4小时)
        long expMillis = nowMillis + 4*3600000L;
        Date exp = new Date(expMillis);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // 输出生成令牌的日志
        log.info("Generated Token for user: {}", username);
        log.info("Token: {}", token);
        log.info("Issued at: {}", now);
        log.info("Expires at: {}", exp);

        return token;
    }

    public static Claims validateToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 输出验证令牌的日志
            log.info("Validated Token: {}", token);
            log.info("Subject: {}", claims.getSubject());
            log.info("Issued at: {}", claims.getIssuedAt());
            log.info("Expiration: {}", claims.getExpiration());
        } catch (Exception e) {
            log.error("Token validation failed", e);
        }
        return claims;
    }

}