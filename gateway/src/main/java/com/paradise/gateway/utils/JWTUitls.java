package com.paradise.gateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jrf
 * @date 2023-3-30 13:23
 */
public class JWTUitls {

    private static final long EXPIRE_TIME = 60000L;//单位为秒
    private static final String TOKEN_SECRET = "My-Paradise";

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String sign(String user){
        String token = null;
        try {
            Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME * 1000);
            Map<String, Object> map = new HashMap<>();
            map.put("alg", "HS256");
            map.put("type", "JWT");
            token = JWT.create()
                    .withHeader(map)//添加头部
                    .withIssuer("auth0")
                    .withClaim("user", user)//载体中设置uid
                    .withExpiresAt(expireDate)//设置过期时间
                    .withIssuedAt(new Date())//设置签发时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));//secret加密
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static boolean verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT!=null) {
                return true;
            }
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}
