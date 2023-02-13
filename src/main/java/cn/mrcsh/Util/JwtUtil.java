package cn.mrcsh.Util;


import cn.mrcsh.Enum.ROLE;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Slf4j
public class JwtUtil {

    private static final long Token_Timeout = 1000 * 60 * 60 * 24;

    public static String getToken(String username, ROLE role) {
        log.info("123");
        JwtBuilder builder = Jwts.builder();
        String jwtToken = builder
                .setHeaderParam("type", "jwt")
                .setHeaderParam("alg", "HS256")
                .claim("username", username)
                .claim("role", role.toString())
                .setSubject("test")
                .setExpiration(new Date(System.currentTimeMillis() + Token_Timeout))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, username)
                .compact();
        return jwtToken;
    }

    public static boolean checkToken(String Token, String username) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(username).parseClaimsJws(Token);
        System.out.println(claimsJws.getBody().get("role"));
        return true;
    }
}
