package com.blog.board.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    // jwt 생성 및 검증을 위한 키
    private static final String SECURITY_KEY = "jwtseckey!@"; // 데이터 암호화, 복호화
    // jwt 생성
    public String create (String userEmail) {
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 현재시간의 +1시간
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY) // 암호화에 사용될 알고리즘, 키
                .setSubject(userEmail).setIssuedAt(new Date()).setExpiration(exprTime) // jwt 제목, 생성일, 만료일
                .compact();
    }
    // jwt 검증
    public String validate(String token) {
        // 매개변수로 받은 token을 키를 사용해서 복호화
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        // 복호화된 토큰의 payload에서 제목을 가져옴
        return claims.getSubject();
    }
}
