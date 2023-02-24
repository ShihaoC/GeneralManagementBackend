package cn.mrcsh.Util;


import cn.mrcsh.Config.JwtConfig;
import cn.mrcsh.Enum.ROLE;
import io.jsonwebtoken.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static final long Token_Timeout = 1000 * 60 * 60 * 24;

    public static String getToken(String username, ROLE role) {
        JwtBuilder builder = Jwts.builder();
        String jwtToken = builder
                .setHeaderParam("type", "jwt")
                .setHeaderParam("alg", "HS256")
                .claim("username", username)
                .claim("role", role.toString())
                .setSubject("test")
                .setExpiration(new Date(System.currentTimeMillis() + Token_Timeout))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, JwtConfig.sign)
                .compact();
        return jwtToken;
    }

    public static String generateToken(String username){
        Date now = new Date();
        Date expireDate = new Date(now.getTime()+1000*60*60*24);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256,JwtConfig.sign)
                .compact();

    }

    public static Claims checkToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(JwtConfig.sign)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            return null;
        }
    }


    public static boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }
}
