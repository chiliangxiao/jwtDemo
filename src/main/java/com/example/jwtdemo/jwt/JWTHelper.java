package com.example.jwtdemo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * JWT加密解析
 * @author: clx
 * @date: 2019/7/22
 * @version: 1.1.0
 */
@Component
public class JWTHelper {

	public static String JWT_SECRET = "JWT_SECRET";
	
    /**
     * 密钥加密token 获取token
     *
     * @param jwtInfo jwt要加密的信息
     * @param expire 过期时间 单位/秒
     * @return
     * @throws Exception
     */
    public static String generateToken(JWTInfo jwtInfo, int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUserName())
                .claim("userId", jwtInfo.getUserId())
                .claim("userName", jwtInfo.getUserName())
                // 默认7天时间过期
                .setExpiration(DateTime.now().plusSeconds((expire == 0 ? 7 : expire)*3600*24).toDate())
                // 1天时间过期
//                .setExpiration(DateTime.now().plusSeconds(3600*24).toDate())
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes())
                .compact();
        return compactJws;
    }
    
    /**
     * 解析验证token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromToken(String token) throws Exception {
        Jws<Claims> claimsJws = parserToken(token);
        Claims body = claimsJws.getBody();
        return new JWTInfo(getObjectValue(body.get("userId")), getObjectValue(body.get("userName")));
    }


    private static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }

}
